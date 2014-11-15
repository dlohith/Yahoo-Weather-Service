package com.phunware.webservice.forecast;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phunware.webservice.domain.WeatherForecast;
import com.phunware.webservice.domain.ZipCode;
import com.phunware.webservice.forecastjson.Forecast;

@Service
public class WeatherForecastFactory {

	@Autowired
	private ZipCodeFactory zipCodeFactory;
	
	public WeatherForecast createWeatherObject(String code, List<Forecast> forecasts){
	
		ZipCode zipCode = zipCodeFactory.createZipCode(code);
		
		return new WeatherForecast(zipCode, forecasts);
	}
}
