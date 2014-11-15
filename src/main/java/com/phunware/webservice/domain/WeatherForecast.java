package com.phunware.webservice.domain;

import java.util.List;

import com.phunware.webservice.forecastjson.Forecast;


public class WeatherForecast {
	
	public WeatherForecast(ZipCode zipCode, List<Forecast> forecasts){
		this.zipCode = zipCode;
		this.forecasts = forecasts;
	}
	
	private ZipCode zipCode;
	private List<Forecast> forecasts;
	
	
	public ZipCode getZipCode() {
		return zipCode;
	}
	public void setZipCode(ZipCode zipCode) {
		this.zipCode = zipCode;
	}
	public List<Forecast> getForecasts() {
		return forecasts;
	}
	public void setForecasts(List<Forecast> forecasts) {
		this.forecasts = forecasts;
	}

}
