package com.test.Data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Constant {
	
	private static Logger logger = Logger.getLogger(Constant.class);
	public static String Domain ;
	public static String CaseConfigDir;

	final static String DOMAIN_KEY = "eshare.qa.api.domain";
	final static String CASE_CONFIG_KEY ="eshare.qa.api.excel";
	final static String propertiesDir = Constant.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	final static String propertiesPath = propertiesDir+"properties/"+"eshare.qa.api.properties";
	
	static 
	{
//		String propertiesDir = Constant.class.getProtectionDomain().getCodeSource().getLocation().getPath();
//		String propertiesPath  = propertiesDir +"properties/"+"eshare.qa.api.properties";
		Properties prop = new Properties();
		try {
			FileInputStream is=new FileInputStream(propertiesPath);
			prop.load(is);
			
			logger.info("Read key " +Constant.DOMAIN_KEY);
			Domain = prop.getProperty(Constant.DOMAIN_KEY);
			
			logger.info("Read key " +Constant.CASE_CONFIG_KEY);
			String caseconfig = prop.getProperty(Constant.CASE_CONFIG_KEY);
			CaseConfigDir =propertiesDir+ caseconfig;
			
			
		} catch (FileNotFoundException e) {
			logger.fatal("file "+propertiesPath +" not foud ! error info :" + e.toString());
			System.exit(0);

		} catch (IOException e) {
			logger.fatal("read properties file error : " + propertiesPath + "error info " + e.toString() );
			System.exit(0);
		}
		
	}



}
