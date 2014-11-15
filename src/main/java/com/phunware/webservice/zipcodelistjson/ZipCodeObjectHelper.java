package com.phunware.webservice.zipcodelistjson;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ZipCodeObjectHelper {
	
	private static final Logger logger = LoggerFactory
			.getLogger(ZipCodeObjectHelper.class);

	public static ZipCodeObject getZipCodeObject(String json){
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			ZipCodeObject zipCodeObject = objectMapper.readValue(json,
					ZipCodeObject.class);
			return zipCodeObject;
		} catch (JsonParseException e) {
			logger.error("JSON Parsing error : ", e);
		} catch (JsonMappingException e) {
			logger.error("JSON Mapping error : ", e);
		} catch (IOException e) {
			logger.error("IO Unknown exception : ", e);
		} catch (RuntimeException e) {
			logger.error("Unknown exception : ", e);
		}
		return null;
	}
}
