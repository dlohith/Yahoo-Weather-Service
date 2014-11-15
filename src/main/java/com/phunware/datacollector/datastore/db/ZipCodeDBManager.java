package com.phunware.datacollector.datastore.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.db4o.ObjectSet;
import com.phunware.datacollector.Factory.ZipCodeFactory;
import com.phunware.datacollector.domain.ZipCode;

@Service
@PropertySource(value = "classpath:/env.properties")
public class ZipCodeDBManager {

	@Autowired
	private Environment env;

	@Autowired
	private DB4ODatabaseManager dbManager;
	
	@Autowired
	private ZipCodeFactory zipCodeFactory;

	private static final Logger logger = LoggerFactory
			.getLogger(ZipCodeDBManager.class);

	@PostConstruct
	public void loadZipCode() {
		logger.info("Starting to load all the zip code objects");
		try {
			String zipCodeAbosultePath = getZipCodeAbsolutePath();
			loadZipCode(zipCodeAbosultePath);
		} catch (UnsupportedEncodingException e) {
			logger.error("Unable to get the class path decoded : ", e);
		}
		
		logger.info("Count zipcode objects : "+this.getAllZipCodes().size());

	}

	private String getZipCodeAbsolutePath() throws UnsupportedEncodingException {
		String zipcodeFolderName = env.getProperty("zipcode_folder")
				+ File.separator;
		String zipcodeFileName = env.getProperty("zipcodefilename");

		String classPath = URLDecoder.decode(this.getClass()
				.getProtectionDomain().getCodeSource().getLocation().getPath(),
				"UTF-8");
		StringBuffer zipcodeAbsolutePath = new StringBuffer();
		zipcodeAbsolutePath.append(classPath.substring(0,
				classPath.indexOf("classes")));
		zipcodeAbsolutePath.append("classes");
		zipcodeAbsolutePath.append(File.separator);
		zipcodeAbsolutePath.append(zipcodeFolderName);
		zipcodeAbsolutePath.append(File.separator);
		zipcodeAbsolutePath.append(zipcodeFileName);

		return zipcodeAbsolutePath.toString();
	}

	private void loadZipCode(String zipcodeFullPath) {

		if (zipcodeFullPath == null) {
			return;
		}
		File zipcodeListFile = new File(zipcodeFullPath);
		List<ZipCode> existingZipCodes = getAllZipCodes();
		if (zipcodeListFile.exists()) {
			logger.info("Reading zipcode from the list");
			readZipCodeLinebyLineAndStore(zipcodeListFile,existingZipCodes);
		}
	}

	private void readZipCodeLinebyLineAndStore(File zipcodeListFile,List<ZipCode> existingZipCodes) {
		BufferedReader  in = null;
		List<ZipCode> zipCodes = new ArrayList<ZipCode>();
		try {
			in = new BufferedReader(new FileReader(zipcodeListFile));
			
			String line;
			while((line = in.readLine()) != null){
				line = line.trim();
				logger.info("Creating and storing zipcode object for : "+line);
				ZipCode zipCode = zipCodeFactory.createZipCode(line);
				if(!existingZipCodes.contains(zipCode)){
					zipCodes.add(zipCode);
				}
				
			}
		} catch (IOException e) {
			logger.error("IO issue while creating input stream to file", e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("IO issue closing the input stream", e);
				}
			}
		}
		if(zipCodes.size() > 0){
			updateZipCodeList(zipCodes);
		}
		logger.info("Created and saved zipcodes of count : "+zipCodes.size());
	}
	

	
	private synchronized void updateZipCodeList(List<ZipCode> zipCodes) {
		for(ZipCode code : zipCodes){
			dbManager.getClient().store(code);
		}
		dbManager.getClient().commit();
	}
	
	public List<ZipCode> getAllZipCodes(){
		ObjectSet<ZipCode> objectSet = dbManager.getClient().query(ZipCode.class);
		List<ZipCode> zipCodes = new ArrayList<ZipCode>(objectSet);
		return zipCodes;
	}
	
	
	public ZipCode getZipCodeByCode(String code){
		ZipCode zipCode = zipCodeFactory.createZipCode(code);
		ObjectSet<ZipCode> foundCodes = dbManager.getClient().queryByExample(zipCode);
		if(foundCodes.size() == 0)
			return null;
		
		return foundCodes.get(0);
	}

}
