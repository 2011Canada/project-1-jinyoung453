package com.revature.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerSingleton {
	
	private static Logger project1Logger = LogManager.getLogger("proj1");
	
	public static Logger getLogger() {
		return project1Logger;
	}

}