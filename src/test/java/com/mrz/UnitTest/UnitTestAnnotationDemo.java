package com.mrz.UnitTest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
/*
 * this is to explain the method execute order for with Annotation
 */
public class UnitTestAnnotationDemo {
	static Log logger = LogFactory.getLog(UnitTestAnnotationDemo.class);

	@Before
	public void before() {
		logger.info("before");
	}

	@After
	public void after() {
		logger.info("after");
	}

	@BeforeClass
	public void beforeClass() {
		logger.info("BeforeClass");
	}

	@AfterClass
	public void afterClass() {
		logger.info("afterClass");
	}

	@Test
	public void test1() {
		logger.info("test1");
	}

	@Test
	public void test2() {
		logger.info("test2");
	}

}
