package com.TeamNovus.Persistence.Databases.H2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import com.TeamNovus.Persistence.Database;
import com.TeamNovus.Persistence.Internal.ColumnRegistration;
import com.TeamNovus.Persistence.Internal.TableRegistration;

public class H2Database extends Database {
	private H2Configuration configuration;
	
	// The connection object.
	private Connection connection;
	
	public H2Database(H2Configuration configuration) {
		this.configuration = configuration;
	}
	
	@Override
	public void connect() {
		try {
			Class.forName("org.h2.Driver");
			
			String url = String.format("jdbc:sqlite:%s", 
											configuration.getPath());

			connection = DriverManager.getConnection(url);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	
	}
	
	@Override
	public void disconnect() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public <T> boolean exists(Class<T> objectClass, Object key) {
		TableRegistration table = getTableRegistration(objectClass);
		
		try {
			// Build the query.
			String query = String.format("SELECT COUNT(*) FROM %s WHERE %s = ?", 
											table.getName(), 
											table.getKey().getName());
			
			// Prepare and execute.
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setObject(1, key);
						
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				return result.getInt(1) >= 1;
			}
			
			result.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	public <T> void save(Class<T> objectClass, T object) {
		TableRegistration table = getTableRegistration(objectClass);
		
		LinkedList<Object> arguments = new LinkedList<Object>();
		
		try {
			table.getKey().getField().setAccessible(true);
			if(exists(objectClass, table.getKey().getField().get(object).toString())) {				
				// Build the query.
				String query = String.format("UPDATE %s SET ", table.getName());
				
				for (int i = 0; i < table.getColumns().size(); i++) {	
					ColumnRegistration column = table.getColumns().get(i);
					
					if(i == 0) {
						query += String.format(" %s = ?", column.getName());
					} else {
						query += String.format(", %s = ?", column.getName());
					}
										
					column.getField().setAccessible(true);
					arguments.add(column.getField().get(object));
				}
				
				query += String.format(" WHERE %s = ?", table.getKey().getName());
				
				table.getKey().getField().setAccessible(true);
				arguments.add(table.getKey().getField().get(object));
			
				// Prepare and execute.
				PreparedStatement statement = connection.prepareStatement(query);
				
				for (int i = 0; i < arguments.size(); i++) {
					statement.setObject(i + 1, arguments.get(i));
				}
				
				statement.execute();
				statement.close();
			} else {
				// Build the query.
				String query = String.format("INSERT INTO %s (", table.getName());
				
				for (int i = 0; i < table.getColumns().size(); i++) {		
					ColumnRegistration column = table.getColumns().get(i);
					
					if(i == 0) {
						query += String.format("%s", column.getName());
					} else {
						query += String.format(", %s", column.getName());
					}					
				}
				
				query += String.format(")");
				
				query += String.format(" VALUES (");
				
				for (int i = 0; i < table.getColumns().size(); i++) {		
					ColumnRegistration column = table.getColumns().get(i);
					
					if(i == 0) {
						query += String.format("?");
					} else {
						query += String.format(", ?");
					}
					
					column.getField().setAccessible(true);
					arguments.add(column.getField().get(object).toString());
				}
				
				query += String.format(")");
				
				// Prepare and execute.
				PreparedStatement statement = connection.prepareStatement(query);
				
				for (int i = 0; i < arguments.size(); i++) {
					statement.setObject(i + 1, arguments.get(i));
				}
								
				statement.execute();
				statement.close();
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public <T> void saveAll(Class<T> objectClass, Iterable<T> objects) {
		Iterator<T> iterator = objects.iterator();
		
		while(iterator.hasNext()) {
			save(objectClass, iterator.next());
		}
	}

	@Override
	public <T> T findOne(Class<T> objectClass, Object key) {
		TableRegistration table = getTableRegistration(objectClass);
		
		try {
			String query = String.format("SELECT * FROM %s WHERE %s = ? LIMIT 1", table.getName(), table.getKey().getName());
			
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setObject(1, key);
			
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				T object = objectClass.newInstance();
				
				for(ColumnRegistration column : table.getColumns()) {
					column.getField().setAccessible(true);
					column.getField().set(object, result.getObject(column.getName()));
				}
				
				return object;
			}
			
			result.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}		
		
		return null;
	}

	@Override
	public <T> LinkedList<T> findAll(Class<T> objectClass) {
		TableRegistration table = getTableRegistration(objectClass);
		
		LinkedList<T> objects = new LinkedList<T>();
		
		try {
			String query = String.format("SELECT * FROM %s", table.getName());
			
			PreparedStatement statement = connection.prepareStatement(query);
						
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				T object = objectClass.newInstance();
				
				for(ColumnRegistration column : table.getColumns()) {
					column.getField().setAccessible(true);
					column.getField().set(object, result.getObject(column.getName()));
				}
				
				objects.add(object);
			}
			
			result.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}		
		
		return objects;
	}

	@Override
	public <T> void drop(Class<T> objectClass, Object key) {
		TableRegistration table = getTableRegistration(objectClass);
		
		try {
			if(exists(objectClass, key)) {
				String query = String.format("DELETE FROM %s WHERE %s = ?", table.getName(), table.getKey().getName());
				
				PreparedStatement statement = connection.prepareStatement(query);
				
				statement.setObject(1, key);
							
				statement.execute();
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public <T> void dropAll(Class<T> objectClass, Iterable<T> objects) {
		TableRegistration table = getTableRegistration(objectClass);
		
		Iterator<T> iterator = objects.iterator();
		
		while(iterator.hasNext()) {
			try {
				table.getKey().getField().setAccessible(true);
				drop(objectClass, table.getKey().getField().get(iterator.next()));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public <T> void updateStructure(Class<T> objectClass) {
		TableRegistration table = getTableRegistration(objectClass);
		
		try {
			// Create the table if it does not exist.
			String createStructureQuery = String.format("CREATE TABLE IF NOT EXISTS %s (", table.getName());
			
			for (int i = 0; i < table.getColumns().size(); i++) {	
				ColumnRegistration column = table.getColumns().get(i);
				
				if(i == 0) {
					createStructureQuery += String.format("%s %s", column.getName(), column.getType());
				} else {
					createStructureQuery += String.format(", %s %s", column.getName(), column.getType());
				}									
			}
			
			createStructureQuery += String.format(")");
			
			// Prepare and Execute.
			PreparedStatement createStructureStatement = connection.prepareStatement(createStructureQuery);
						
			createStructureStatement.executeUpdate();
			createStructureStatement.close();
			
			// Check the structure of the table and alter as necessary.
			String checkStructureQuery = String.format("SELECT * FROM %s LIMIT 1", table.getName());
			
			PreparedStatement checkStructureStatement = connection.prepareStatement(checkStructureQuery);
			ResultSetMetaData meta = checkStructureStatement.executeQuery().getMetaData();
			checkStructureStatement.close();
			
			LinkedHashMap<String, String> toAdd = new LinkedHashMap<String, String>();
			LinkedHashMap<String, String> toModify = new LinkedHashMap<String, String>();
			for(ColumnRegistration column : table.getColumns()) {
				boolean found = false;
				for (int i = 1; i <= meta.getColumnCount(); i++) {
					if(column.getName().equals(meta.getColumnName(i))) {
						found = true;
						
						String type = meta.getColumnTypeName(i);
						
						if (!(column.getType().equals(type))){
							toModify.put(column.getName(), column.getType());
						}
						
						break;
					}
				}
				
				if(!(found)) {
					toAdd.put(column.getName(), column.getType());
				}
			}
			
			String alterStructureQuery = String.format("ALTER TABLE %s ", table.getName());
			
			for(String columnName : toAdd.keySet()) {
				alterStructureQuery += String.format("ADD COLUMN %s %s, ", columnName, toAdd.get(columnName));
			}
			
			for(String columnName : toModify.keySet()) {
				alterStructureQuery += String.format("MODIFY COLUMN %s %s, ", columnName, toModify.get(columnName));
			}
			
			if(toAdd.size() > 0 || toModify.size() > 0) {
				alterStructureQuery = alterStructureQuery.substring(0, alterStructureQuery.length() - 2);
			}
			
			PreparedStatement alterStructureStatement = connection.prepareStatement(alterStructureQuery);
						
			alterStructureStatement.executeUpdate();
			alterStructureStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

			
	}

}
