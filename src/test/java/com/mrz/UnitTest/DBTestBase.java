package com.mrz.UnitTest;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.operation.DatabaseOperation;


public abstract class DBTestBase extends DataSourceBasedDBTestCase {
	private BasicDataSource dataSource; 
	public DBTestBase(){
		
	}
	
	public DBTestBase(String name){
		super(name);
	}
	
	@Override
	protected DataSource getDataSource() {
		return dataSource;
	}
	
	@Override
	protected void setUp() throws Exception{
		setUpDataSource();
		super.setUp();

	}
	
	private void setUpDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(System.getProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS));
		dataSource.setUrl(System.getProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL));
		dataSource.setUsername(System.getProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME));
		dataSource.setPassword(System.getProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD));
		dataSource.setDefaultAutoCommit(true);
		dataSource.setAccessToUnderlyingConnectionAllowed(true);
		this.dataSource = dataSource;

	}

	@Override
	protected void setUpDatabaseConfig(DatabaseConfig config){
		
		super.setUpDatabaseConfig(config);
	}
	
	@Override
	protected DatabaseOperation getSetUpOperation() throws Exception{
		return DatabaseOperation.CLEAN_INSERT;
	}
	@Override
	protected DatabaseOperation getTearDownOperation() throws Exception{
		return DatabaseOperation.NONE;
	}
	protected void closeDataSource() throws Exception{
		this.dataSource.close();
	}
}
