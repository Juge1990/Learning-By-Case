package com.mrz.log.commonLogging;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mrz.log.packageLevel.Log4JPackageLevelDemo;

public class comLog {

	static Log log = LogFactory.getLog(comLog.class);

	public static void main(String[] args) {

		log.debug("Here is some DEBUG");
		log.info("Here is some INFO");
		log.warn("Here is some WARN");
		log.error("Here is some ERROR");
		log.fatal("Here is some FATAL");
		Log4JPackageLevelDemo demo = new Log4JPackageLevelDemo();
		demo.logInPackageLevel();
	}
}
