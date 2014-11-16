package com.phunware.ui.service;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ForecastData {
	
	private static final Logger logger = LoggerFactory
			.getLogger(ForecastData.class);
	
	@Autowired
	@Qualifier("webserviceendpoint")
	private String webserviceendpoint;
	
	private static final String ZIPCODE = "zipcode";
	private static final String ZIPCODELIST = "zipcodes";
	
	public String getForecastForZipCode(String zipcode){
		String requestJson = prepareRequestJSON(zipcode);
		logger.info("Request JSON " +requestJson);
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		RestTemplate restTemplate = new RestTemplate();
		List<MediaType> mediaTypes = new ArrayList<MediaType>();
		mediaTypes.add(MediaType.APPLICATION_JSON);
		messageConverters.add(new FormHttpMessageConverter());
		messageConverters.add(new StringHttpMessageConverter());
		restTemplate.setMessageConverters(messageConverters);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(mediaTypes);
		HttpEntity request = new HttpEntity(requestJson,headers);
		String response = null;
		try{
			response = restTemplate.postForObject(webserviceendpoint, request,String.class);
		}catch(Exception e){
			logger.error("",e);
		}
		return response;
	}
	
	private String prepareRequestJSON(String zipcode){
		
		JSONObject zipcodeJSON = new JSONObject();
		zipcodeJSON.put(ZIPCODE, zipcode);
				
		JSONArray zipcodeJSONArray = new JSONArray();
		zipcodeJSONArray.add(zipcodeJSON);
		
		JSONObject requestJSON = new JSONObject();
		requestJSON.put(ZIPCODELIST,zipcodeJSONArray);
		
		return requestJSON.toJSONString();
	}

}
