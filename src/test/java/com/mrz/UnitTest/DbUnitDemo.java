package com.mrz.UnitTest;

import java.sql.SQLException;
import java.util.List;

import org.dbunit.DatabaseUnitException;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.DefaultDataSet;
import org.dbunit.dataset.DefaultTable;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.operation.DatabaseOperation;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mrz.collections.Student;

public class DbUnitDemo extends DBTestBase {
	StudentDao dao;

	@BeforeClass
	@Override
	public void setUp() throws Exception {
		System.setProperty(
				PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,
				"com.mysql.jdbc.Driver");
		System.setProperty(
				PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,
				"jdbc:mysql://localhost:3306/demo");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,
				"root");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,
				"");
		super.setUp();
		dao = new StudentDaoImpl();
		dao.setDataSource(getDataSource());
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new DefaultDataSet(new ITable[] { new DefaultTable(
				Tables.STUDENT) });

	}

	@Test
	public void test_should_get_twosstudents_when_get_student_by_name()
			throws DatabaseUnitException, SQLException, Exception {
		DefaultTable students = new DefaultTable(Tables.STUDENT);
		students.addRow(new Object[] { 2, "zhao", 26 });
		students.addRow(new Object[] { 3, "zhao", 26 });
		// set up database in a consistent state before each test
		DatabaseOperation.CLEAN_INSERT.execute(getConnection(),
				new DefaultDataSet(new ITable[] { students }));
		// Test DB function
		List<Student> stus = dao.getStudentByName("zhao");
		assertTrue(stus.size() == 2 && "zhao".equals(stus.get(0).getName()));
	}

}
