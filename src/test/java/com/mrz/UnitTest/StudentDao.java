package com.mrz.UnitTest;

import java.util.List;

import javax.sql.DataSource;

import com.mrz.collections.Student;

public class StudentDao {
	DataSource dataSource;

	public StudentDao() {
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Student> getStudentByName(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
