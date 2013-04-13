package com.TeamNovus.Persistence.Databases.MySQL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.TeamNovus.Persistence.Annotations.Relationships.CascadeType;
import com.TeamNovus.Persistence.Databases.Database;
import com.TeamNovus.Persistence.Internal.ColumnRegistration;
import com.TeamNovus.Persistence.Internal.SubTableRegistration;
import com.TeamNovus.Persistence.Internal.TableRegistration;

public class MySQLDatabase extends Database {
	private MySQLConfiguration configuration;
	private Connection connection;

	public MySQLDatabase(MySQLConfiguration configuration) {
		this.configuration = configuration;
	}

	public void connect() {
		super.connect();

		try {
			String url = String.format("jdbc:mysql://%s:%s/%s", 
					configuration.getHost(), 
					configuration.getPort(), 
					configuration.getDatabase());

			connection = DriverManager.getConnection(url, configuration.getUsername(), configuration.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		super.disconnect();

		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String convertToType(ColumnRegistration column) {
		if(column.getType().equals(Integer.class) || column.getType().equals(int.class)) {
			return "INT";
		} else if(column.getType().equals(String.class)) {
			return "LONG VARCHAR";
		} else if(column.getType().equals(Long.class) || column.getType().equals(long.class)) {
			return "BIGINT";
		} else if(column.getType().equals(Double.class) || column.getType().equals(double.class)) {
			return "DOUBLE";
		} else if(column.getType().equals(Float.class) || column.getType().equals(float.class)) {
			return "FLOAT";
		} else if(column.getType().equals(Boolean.class) || column.getType().equals(boolean.class)) {
			return "BOOLEAN";
		} else if(column.getType().equals(Date.class)) {
			return "DATE";
		}

		return "";
	}
	
	public void createStructure(Class<?> objectClass) {
		if(isDisconnected()) {
			connect();
		}

		try {
			TableRegistration table = getTableRegistration(objectClass);

			String query = String.format("CREATE TABLE IF NOT EXISTS %s (", table.getName());

			for (int i = 0; i < table.getColumns().size(); i++) {
				ColumnRegistration column = table.getColumns().get(i);

				// Build the column parameters:
				String type = " " + convertToType(column);
				String notNull = column.isNotNull() ? " NOT NULL" : "";
				String unique =  column.isUnique() ? " UNIQUE" : "";
				String autoIncrement = (table.getId().getName().equals(column.getName()) ? " AUTO_INCREMENT PRIMARY KEY" : "");

				if(i == 0) {
					query += column.getName() + type + notNull + unique + autoIncrement;
				} else {
					query += ", " + column.getName() + type + notNull + unique + autoIncrement;
				}
			}

			query += ")";
						
			PreparedStatement statement = connection.prepareStatement(query);

			statement.execute();
			statement.close();

			for(SubTableRegistration subTable : table.getSubTables()) {
				createStructure(subTable.getTableClass());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateStructure(Class<?> objectClass) {
		if(isDisconnected()) {
			connect();
		}

		try {
			TableRegistration table = getTableRegistration(objectClass);

			String query = String.format("SELECT * FROM %s LIMIT 1", table.getName());
			PreparedStatement statement = connection.prepareStatement(query);

			ResultSetMetaData meta = statement.executeQuery().getMetaData();

			LinkedList<ColumnRegistration> toChange = new LinkedList<ColumnRegistration>();
			LinkedList<ColumnRegistration> toAdd = new LinkedList<ColumnRegistration>();

			for(ColumnRegistration column : table.getColumns()) {
				boolean found = false;

				for (int i = 1; i <= meta.getColumnCount(); i++) {
					if(column.getName().equals(meta.getColumnName(i))) {
						found = true;

						if(!(convertToType(column).equals(meta.getColumnTypeName(i)))) {
							toChange.add(column);
							break;
						}						
					}
				}

				if(!(found)) {
					toAdd.add(column);
				}
			}
			
			statement.close();

			for(ColumnRegistration column : toChange) {
				String type = " " + convertToType(column);
				String notNull = column.isNotNull() ? " NOT NULL" : "";
				String unique =  column.isUnique() ? " UNIQUE" : "";
				String autoIncrement = (table.getId().equals(column) ? " AUTO_INCREMENT" : "");

				String modify = String.format("ALTER TABLE %s MODIFY COLUMN %s" + type + notNull + unique + autoIncrement, table.getName(), column.getName());
				PreparedStatement modifyStatement = connection.prepareStatement(modify);

				modifyStatement.execute();
			}

			for(ColumnRegistration column : toAdd) {
				String type = " " + convertToType(column);
				String notNull = column.isNotNull() ? " NOT NULL" : "";
				String unique =  column.isUnique() ? " UNIQUE" : "";
				String autoIncrement = (table.getId().equals(column) ? " AUTO_INCREMENT" : "");

				String add = String.format("ALTER TABLE %s ADD COLUMN %s" + type + notNull + unique + autoIncrement, table.getName(), column.getName());

				PreparedStatement addStatement = connection.prepareStatement(add);

				addStatement.execute();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public <T> T find(Class<T> objectClass, Integer id) {
		if(isDisconnected()) {
			connect();
		}

		try {
			TableRegistration table = getTableRegistration(objectClass);

			String query = String.format("SELECT * FROM %s WHERE %s = ?", table.getName(), table.getId().getName());

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);

			ResultSet result = statement.executeQuery();

			while(result.next()) {
				T object = objectClass.newInstance(); 

				for(ColumnRegistration column : table.getColumns()) {
					column.setValue(object, result.getObject(column.getName()));
				}

				result.close();
				statement.close();

				loadRelationshipObjects(object);

				return object;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public <T> List<T> findBy(Class<T> objectClass, String condition, Object... params) {
		if(isDisconnected()) {
			connect();
		}

		LinkedList<T> entries = new LinkedList<T>();

		try {
			TableRegistration table = getTableRegistration(objectClass);

			String query = String.format("SELECT * FROM %s", table.getName(), table.getId().getName());

			// Append the condition:
			if(condition != "" || condition != null) {
				query += " WHERE " + condition;
			}

			PreparedStatement statement = connection.prepareStatement(query);

			for (int i = 0; i < params.length; i++) {
				statement.setObject(i + 1, params[i]);
			}

			ResultSet result = statement.executeQuery();

			// Build the objects:
			while(result.next()) {
				T object = objectClass.newInstance(); 

				for(ColumnRegistration column : table.getColumns()) {
					column.setValue(object, result.getObject(column.getName()));
				}

				loadRelationshipObjects(object);

				entries.add(object);
			}

			result.close();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return entries;
	}

	public void save(Object object) {
		if(isDisconnected()) {
			connect();
		}

		try {
			TableRegistration table = getTableRegistration(object.getClass());

			if(table.getId().getValue(object) == null) {
				LinkedList<Object> arguments = new LinkedList<Object>();
				String query = String.format("INSERT INTO %s (", table.getName());

				for (int i = 0; i < table.getColumns().size(); i++) {		
					ColumnRegistration column = table.getColumns().get(i);

					if(i == 0) {
						query += String.format("%s", column.getName());
					} else {
						query += String.format(", %s", column.getName());
					}					
				}

				query += ") VALUES (";

				for (int i = 0; i < table.getColumns().size(); i++) {		
					ColumnRegistration column = table.getColumns().get(i);

					if(i == 0) {
						query += "?";
					} else {
						query += ", ?";
					}

					arguments.add(column.getValue(object));
				}

				query += String.format(")");

				// Prepare and execute.
				PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

				for (int i = 0; i < arguments.size(); i++) {
					statement.setObject(i + 1, arguments.get(i));
				}

				statement.execute();

				ResultSet result = statement.getGeneratedKeys();

				if (result.next()){
					table.getId().setValue(object, result.getInt(1));
				}

				result.close();
				statement.close();
			} else {
				LinkedList<Object> arguments = new LinkedList<Object>();
				String query = String.format("UPDATE %s SET ", table.getName());

				for (int i = 0; i < table.getColumns().size(); i++) {	
					ColumnRegistration column = table.getColumns().get(i);

					if(i == 0) {
						query += String.format(" %s = ?", column.getName());
					} else {
						query += String.format(", %s = ?", column.getName());
					}

					arguments.add(column.getValue(object));
				}

				query += String.format(" WHERE %s = ?", table.getId().getName());

				arguments.add(table.getId().getValue(object));

				// Prepare and execute.
				PreparedStatement statement = connection.prepareStatement(query);

				for (int i = 0; i < arguments.size(); i++) {
					statement.setObject(i + 1, arguments.get(i));
				}

				statement.execute();
				statement.close();
			}

			saveRelationshipObjects(object);
			dropRemovedObjects(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void drop(Object object) {
		if(isDisconnected()) {
			connect();
		}

		try {
			TableRegistration table = getTableRegistration(object.getClass());

			String query = String.format("DELETE FROM %s WHERE %s = ?", table.getName(), table.getId().getName());

			PreparedStatement statement = connection.prepareStatement(query);

			statement.setObject(1, table.getId().getValue(object));

			statement.execute();
			statement.close();

			dropRelationshipObjects(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public <T> List<T> findAll(Class<T> objectClass) {
		if(isDisconnected()) {
			connect();
		}

		try {
			TableRegistration table = getTableRegistration(objectClass);

			LinkedList<T> entries = new LinkedList<T>();
			String query = String.format("SELECT * FROM %s", table.getName());

			PreparedStatement statement = connection.prepareStatement(query);

			ResultSet result = statement.executeQuery();

			// Iterate through the results and build the objects.
			while(result.next()) {
				T object = objectClass.newInstance();

				for(ColumnRegistration column : table.getColumns()) {
					column.setValue(object, result.getObject(column.getName()));
				}

				// Cascade Load:
				loadRelationshipObjects(object);

				entries.add(object);
			}

			result.close();
			statement.close();

			return entries;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new LinkedList<T>();
	}

	public void saveAll(Iterable<?> objects) {
		Iterator<?> iterator = objects.iterator();
		
		while(iterator.hasNext()) {
			save(iterator.next());
		}
	}

	public void dropAll(Iterable<?> objects) {
		Iterator<?> iterator = objects.iterator();
		
		while(iterator.hasNext()) {
			drop(iterator.next());
		}
	}

	public void loadRelationshipObjects(Object object) {
		if(isDisconnected()) {
			connect();
		}

		try {
			TableRegistration table = getTableRegistration(object.getClass());

			for(SubTableRegistration subTable : table.getSubTables()) {
				if(subTable.getCascadeType().equals(CascadeType.NONE)) {
					continue;
				}
				
				switch (subTable.getRelationshipType()) {
				case ONE_TO_ONE:
					subTable.getParentField().setAccessible(true);

					Object data = null;

					try {
						data = findBy(subTable.getTableClass(), subTable.getForeignKey().getName() + " = ?", (Integer) table.getId().getValue(object)).get(0);
					} catch(IndexOutOfBoundsException ignored) { }

					if(data != null)
						subTable.getParentField().set(object, data);
					break;

				case ONE_TO_MANY:
					subTable.getParentField().setAccessible(true);

					List<?> children = findBy(subTable.getTableClass(), subTable.getForeignKey().getName() + " = ?", table.getId().getValue(object));

					if(!(children.equals(null)))
						subTable.getParentField().set(object, children);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveRelationshipObjects(Object object) {
		if(isDisconnected()) {
			connect();
		}

		try {
			TableRegistration table = getTableRegistration(object.getClass());

			for(SubTableRegistration subTable : table.getSubTables()) {
				if(subTable.getCascadeType().equals(CascadeType.NONE)) {
					continue;
				}
				
				// Prepare the child for saving.
				Object child = null;

				// Prepare the field.
				subTable.getParentField().setAccessible(true);

				// Determine what type of relationship to save.
				switch (subTable.getRelationshipType()) {
				case ONE_TO_ONE:
					child = subTable.getParentField().get(object);

					// Update the child's foreign key
					subTable.getForeignKey().setValue(child, table.getId().getValue(object));

					save(child);
					break;

				case ONE_TO_MANY:
					List<?> children = (List<?>) subTable.getParentField().get(object);

					Iterator<?> iterator = children.iterator();

					while(iterator.hasNext()) {
						child = iterator.next();

						// Update the child's foreign key
						subTable.getForeignKey().setValue(child, table.getId().getValue(object));
					}
					
					saveAll(children);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void dropRelationshipObjects(Object object) {
		if(isDisconnected()) {
			connect();
		}

		try {
			TableRegistration table = getTableRegistration(object.getClass());

			for(SubTableRegistration subTable : table.getSubTables()) {
				if(subTable.getCascadeType().equals(CascadeType.NONE)) {
					continue;
				}
				
				// Prepare the child for saving.
				Object child = null;

				// Prepare the field.
				subTable.getParentField().setAccessible(true);

				// Determine what type of relationship to save.
				switch (subTable.getRelationshipType()) {
				case ONE_TO_ONE:
					child = subTable.getParentField().get(object);

					// Update the child's foreign key
					subTable.getForeignKey().setValue(child, table.getId().getValue(object));

					drop(child);				
					break;

				case ONE_TO_MANY:
					List<?> children = (List<?>) subTable.getParentField().get(object);

					Iterator<?> iterator = children.iterator();

					while(iterator.hasNext()) {
						child = iterator.next();

						// Update the child's foreign key
						subTable.getForeignKey().setValue(child, table.getId().getValue(object));
					}
					
					saveAll(children);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void dropRemovedObjects(Object object) {
		if(isDisconnected()) {
			connect();
		}

		try {
			TableRegistration table = getTableRegistration(object.getClass());

			for(SubTableRegistration subTable : table.getSubTables()) {
				if(subTable.getCascadeType().equals(CascadeType.NONE)) {
					continue;
				}
				
				switch (subTable.getRelationshipType()) {
				case ONE_TO_ONE:
					subTable.getParentField().setAccessible(true);

					// Load the stored data:
					Object storedData = null;

					try {
						storedData = findBy(subTable.getTableClass(), subTable.getForeignKey().getName() + " = ?", (Integer) table.getId().getValue(object)).get(0);
					} catch(IndexOutOfBoundsException ignored) { }

					// Load the current object data:
					Object currentData = null;

					try {
						currentData = subTable.getParentField().get(object);
					} catch (Exception e) { }

					// Check if the insertion id's are the same:
					if(storedData != null && currentData != null && !(subTable.getId().getValue(currentData).equals(subTable.getId().getValue(storedData)))) {
						drop(storedData);
					}
					break;

				case ONE_TO_MANY:
					subTable.getParentField().setAccessible(true);

					List<?> storedChildren = findBy(subTable.getTableClass(), subTable.getForeignKey().getName() + " = ?", table.getId().getValue(object));
					List<?> currentChildren = (List<?>) subTable.getParentField().get(object);

					List<Object> toRemove = new ArrayList<Object>();

					for(Object storedObject : storedChildren) {
						boolean remove = true;

						for(Object currentObject : currentChildren) {
							if(subTable.getId().getValue(currentObject).equals(subTable.getId().getValue(storedObject))) {
								remove = false;
								break;
							}
						}

						if(remove)
							toRemove.add(storedObject);
					}

					dropAll(toRemove);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ResultSet execute(String query, Object... params) {
		if(isDisconnected()) {
			connect();
		}

		try {
			PreparedStatement statement = connection.prepareStatement(query);

			for (int i = 0; i < params.length; i++) {
				statement.setObject(i + 1, params[i]);
			}

			return statement.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public void beginTransaction() {
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void endTransaction() {
		try {
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
