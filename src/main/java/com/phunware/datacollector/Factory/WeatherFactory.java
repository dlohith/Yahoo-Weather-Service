package com.phunware.datacollector.Factory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phunware.datacollector.domain.Weather;
import com.phunware.datacollector.domain.ZipCode;
import com.phunware.datacollector.forecastjson.Forecast;

@Service
public class WeatherFactory {

	@Autowired
	private ZipCodeFactory zipCodeFactory;
	
	public Weather createWeatherObject(String code, List<Forecast> forecasts){
	
		ZipCode zipCode = zipCodeFactory.createZipCode(code);
		
		return new Weather(zipCode, forecasts);
	}
}
