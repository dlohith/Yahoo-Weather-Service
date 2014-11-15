package com.phunware.datacollector.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.phunware.datacollector.forecastjson.Forecast;

public class Weather {

	private static final Logger logger = LoggerFactory
			.getLogger(Weather.class);
	
	public Weather(ZipCode zipCode, List<Forecast> forecasts){
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
	
	public boolean isForecastUptodate(){
		boolean today = false;
		boolean newDate = false;
		boolean old = false;
		for(Forecast forecast : forecasts){
			int val = dateCompareValue(forecast.getDate());
			if(val == 0){
				today = true;
			}
			if(val < 0){
				old = true;
			}
			if(val > 0){
				newDate = true;
			}
		}
		return (newDate == true) && (old == false) && (today == true);
	}
	
	private static int dateCompareValue(String dateString){
		SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy");
		try {
			Date date = sdf.parse(sdf.format(new Date() ));
			return sdf.parse(dateString).compareTo(date);
		} catch (java.text.ParseException e) {
			logger.error("Date parse exception : ", e);
		} catch (RuntimeException e){
			logger.error("Unknown Date parse exception : ", e);
		}
		return -2;
	}
	
	@Override
	public boolean equals(Object o) {

		if (o == this) {
			return true;
		}

		if (!(o instanceof Weather)) {
			return false;
		}
		 
		Weather weather = (Weather) o;
		 
		return this.zipCode.equals(weather.getZipCode());
	}
	
	@Override
	public int hashCode(){
		
		int hashcode = 37;
		hashcode = hashcode * 17 * this.zipCode.hashCode();
		
		return hashcode;
		
	}
	
	
}
