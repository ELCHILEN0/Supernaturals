package com.TeamNovus.Persistence;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;

import com.TeamNovus.Persistence.Exceptions.TableRegistrationException;
import com.TeamNovus.Persistence.Internal.TableRegistration;
import com.TeamNovus.Persistence.Internal.TableRegistrationFactory;

public abstract class Database {
	private boolean checkTableOnRegistration = true;
	private HashSet<TableRegistration> tables = new HashSet<TableRegistration>();

	public void registerTable(Class<?> tableClass) throws TableRegistrationException {
		
		TableRegistration table = null;
		
		try {
			table = TableRegistrationFactory.createTableRegistration(tableClass);
		} catch (TableRegistrationException e) {
			e.printStackTrace();
		}
		
		if(table == null) {
			throw new TableRegistrationException("The TableFactory returned null.");
		}
		
		if(isRegistered(tableClass)) {
			throw new TableRegistrationException("The specified table is already registered.");
		}
		
		// Add the table to the registered tables
		tables.add(table);
		
		if(checkTableOnRegistration) {
			updateStructure(table.getTableClass());
		}
	}
	
	public HashSet<TableRegistration> getTables() {
		return tables;
	}
	
	public TableRegistration getTableRegistration(Class<?> tableClass) {
		for(TableRegistration table : tables) {
			if(table.getTableClass().equals(tableClass)) {
				return table;
			}
		}
		
		return null;
	}

	public boolean isRegistered(Class<?> tableClass) {
		return tables.contains(getTableRegistration(tableClass));
	}
	
	public void setCheckTableOnRegistration(Boolean checkTableOnRegistration) {
		this.checkTableOnRegistration = checkTableOnRegistration;
	}

	public Boolean getCheckTableOnRegistration() {
		return checkTableOnRegistration;
	}	
	
	// Abstract Methods to Implement:
	/**
	 * Connect to the database.
	 * 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public abstract void connect() throws ClassNotFoundException, SQLException;
	
	/**
	 * Disconnect from the database.
	 * 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public abstract void disconnect() throws ClassNotFoundException, SQLException;
	
	/**
	 * Check if an object exists with an id.
	 * 
	 * @param objectClass - The class of the object.
	 * @param key - The primary key of the table.
	 * 
	 * @return Whether the object exists or not.
	 * 
	 * @throws TableRegistrationException 
	 */
	public abstract <T> boolean exists(Class<T> objectClass, Object key);
	
	/**
	 * Save an object to the database.
	 * 
	 * @param objectClass - The class of the object.
	 * @param object - The object to save.
	 * 
	 * @throws TableRegistrationException 
	 */
	public abstract <T> void save(Class<T> objectClass, T object);
	
	/**
	 * Save a collection of objects to the database.
	 * 
	 * @param objectClass - The class of the objects.
	 * @param objects - The objects to save.
	 * 
	 * @throws TableRegistrationException 
	 */
	public abstract <T> void saveAll(Class<T> objectClass, Iterable<T> objects);
	
	/**
	 * Select an object by an Identifier.
	 * 
	 * @param objectClass - The class of the object.
	 * @param key - The primary key of the table.
	 * 
	 * @return A method matching the id or null.
	 * 
	 * @throws TableRegistrationException 
	 */
	public abstract <T> T findOne(Class<T> objectClass, Object key);
	
	/**
	 * Select all objects in a table.
	 * 
	 * @param objectClass - The class of the object.
	 * 
	 * @return A LinkedList containing all the objects.
	 * 
	 * @throws TableRegistrationException 
	 */
	public abstract <T> LinkedList<T> findAll(Class<T> objectClass);
		
	/**
	 * Drop an object in a table by an id.
	 * 
	 * @param objectClass - The class of the objects.
	 * @param key - The primary key of the table.
	 * 
	 * @throws TableRegistrationException 
	 */
	public abstract <T> void drop(Class<T> objectClass, Object key);
			
	/**
	 * Drop all objects in a collection from a table.
	 * 
	 * @param objectClass - The class of the objects.
	 * @param objects - The objects to delete.
	 * 
	 * @throws TableRegistrationException 
	 */
	public abstract <T> void dropAll(Class<T> objectClass, Iterable<T> objects);
	
	/**
	 * Update the a tables structure if any modifications are found.
	 * 
	 * @param objectClass - The class of the object.
	 * @throws TableRegistrationException 
	 */
	public abstract <T> void updateStructure(Class<T> objectClass);
	
}
