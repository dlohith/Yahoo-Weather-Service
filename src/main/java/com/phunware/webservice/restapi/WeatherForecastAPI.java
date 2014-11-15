package com.phunware.webservice.restapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.phunware.webservice.datacollector.ForecastData;
import com.phunware.webservice.datacollector.JSONObjectConverter;
import com.phunware.webservice.zipcodelistjson.ZipCodeObject;
import com.phunware.webservice.zipcodelistjson.ZipCodeObjectHelper;

@RestController
public class WeatherForecastAPI {
	
	private static final Logger logger = LoggerFactory
			.getLogger(WeatherForecastAPI.class);
	
	@Autowired
	private ForecastData forecastData;
	
	@RequestMapping(value = "getweatherforecast", method = RequestMethod.POST)  
	public String getForecast(@RequestBody String zipcodelistjson) { 
		
		if(zipcodelistjson == null){
			return JSONObjectConverter.emptyResponse();
		}
		
		ZipCodeObject zipCodeObject = ZipCodeObjectHelper.getZipCodeObject(zipcodelistjson);
		
		if(zipCodeObject != null){
			String weatherForecastJSON = forecastData.getWeatherForecasts(zipCodeObject.getZipcodes());
			logger.info(weatherForecastJSON);
			return weatherForecastJSON;
		}else{
			return JSONObjectConverter.emptyResponse();
		}
	   
	}  
}
