package com.mrz.UnitTest;

import org.dbunit.dataset.Column;
import org.dbunit.dataset.DefaultTableMetaData;
import org.dbunit.dataset.ITableMetaData;
import org.dbunit.dataset.datatype.DataType;

public final class Tables {
	public static final ITableMetaData STUDENT = new DefaultTableMetaData(
			"student", new Column[] {
					new Column("student_id", DataType.NUMERIC),
					new Column("student_name", DataType.CHAR),
					new Column("age", DataType.NUMERIC) });

}
