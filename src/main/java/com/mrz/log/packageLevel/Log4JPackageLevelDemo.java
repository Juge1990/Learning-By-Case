package com.mrz.log.packageLevel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Log4JPackageLevelDemo {
	static Log log = LogFactory.getLog("com.mrz.log.packageLevel");

	public void logInPackageLevel(){
		log.debug("Hello log4j, Debug!");
		log.info("Hello log4j, INFO!");
	}
}
