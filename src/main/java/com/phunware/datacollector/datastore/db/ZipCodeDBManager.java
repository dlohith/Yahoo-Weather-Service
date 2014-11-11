package com.phunware.datacollector.datastore.db;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@PropertySource(value = "classpath:/env.properties")
public class ZipCodeDBManager {

	@Autowired
	private Environment env;
	
	@Autowired 
	DB4ODatabaseManager dbManager;
	
	
	@PostConstruct
	public void loadZipCode() throws UnsupportedEncodingException{
		String zipcodefolder = env.getProperty("zipcode_folder") + File.separator;

		String zipcodePath = zipcodefolder + env.getProperty("zipcodefilename");
		String classPath = URLDecoder.decode(this.getClass()
				.getProtectionDomain().getCodeSource().getLocation().getPath(),
				"UTF-8");
		String zipcodeFullPath = classPath
				.substring(0, classPath.indexOf("classes"))
				+ "classes"
				+ File.separator + zipcodePath;
	}
}
