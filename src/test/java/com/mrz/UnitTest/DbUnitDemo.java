package com.mrz.UnitTest;

import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.DefaultDataSet;
import org.dbunit.dataset.DefaultTable;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class DbUnitDemo extends DBTestBase{

	public DbUnitDemo() {
		// TODO Auto-generated constructor stub
	}
	
	@BeforeClass
	@Override
	public void setUp() throws Exception{
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.mysql.jdbc.Driver");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:mysql://localhost:3306/demo");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "root");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "");
		super.setUp();
	}
	
	@AfterClass
	@Override
	public void tearDown() throws Exception{
		super.tearDown();
		this.closeDataSource();
	}
	

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new DefaultDataSet(new ITable[]{new DefaultTable(Tables.STUDENT)});
		
	}
	
	@Test
	public void test() throws DatabaseUnitException, SQLException, Exception {
		DefaultTable student = new DefaultTable(Tables.STUDENT);
		student.addRow(new Object[]{2, "zhao", 26});
		DatabaseOperation.INSERT.execute(getConnection(), new DefaultDataSet(new ITable[]{student}));
		System.out.print("test done");
		
	}

}
