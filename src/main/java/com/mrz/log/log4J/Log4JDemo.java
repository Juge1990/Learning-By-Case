package com.mrz.log.log4J;

import org.apache.log4j.Logger;

import com.mrz.log.packageLevel.Log4JPackageLevelDemo;

/*
 * Demo for Log4J
 */
public class Log4JDemo {
	static Logger log = Logger.getLogger(Log4JDemo.class);  

	public static void main(String [] agrs){
		log.info("Hello log4j, INFO!");
		log.warn("hello log4J WARN");
		log.error("Hello log4j, ERROR");
		Log4JPackageLevelDemo demo = new Log4JPackageLevelDemo();
		demo.logInPackageLevel();
	}
}
