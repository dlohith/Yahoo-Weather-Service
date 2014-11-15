package com.phunware.datacollector.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.phunware.datacollector.yahooapi.DataCollectorService;

@RestController
public class DataCollectorAPIs {

	private static final Logger logger = LoggerFactory
			.getLogger(DataCollectorAPIs.class);
	
	@Autowired
	private DataCollectorService dataCollectorService;
	
	@RequestMapping(value = "getforecast/{zipcode}", method = RequestMethod.GET)  
	public String getForecast(@PathVariable String zipcode) { 
		
		String forecastJSON =  dataCollectorService.getWeatherForecat(zipcode);
		
		logger.info(forecastJSON);
		
	   
		return forecastJSON;
	}  
	
	
}
