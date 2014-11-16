package com.phunware.datacollector.datastore.db;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.phunware.datacollector.Factory.ZipCodeFactory;
import com.phunware.datacollector.domain.Weather;
import com.phunware.datacollector.domain.ZipCode;

@Service
public class WeatherDBManager {

	@Autowired
	private ZipCodeFactory zipCodeFactory;

	@Autowired
	private DB4ODatabaseManager dbManager;
	
	private static final Logger logger = LoggerFactory
			.getLogger(WeatherDBManager.class);

	public synchronized void storeWeather(Weather weather) {
		Weather oldWeather = getWeatherByZipCode(weather.getZipCode().getZipCode());
		if(oldWeather != null){
			dbManager.getClient().delete(oldWeather);
		}
		dbManager.getClient().store(weather);
		dbManager.getClient().commit();
	}

	public List<ZipCode> getAllZipCodes() {
		ObjectSet<ZipCode> objectSet = dbManager.getClient().query(
				ZipCode.class);
		List<ZipCode> zipCodes = new ArrayList<ZipCode>(objectSet);
		return zipCodes;
	}

	public Weather getWeatherByZipCode(final String zipcode) {

		ObjectSet<Weather> result = dbManager.getClient().query(
				new Predicate<Weather>() {
					private static final long serialVersionUID = 1L;

					@Override
					public boolean match(Weather weather) {
						return weather.getZipCode().getZipCode()
								.equals(zipcode);
					}
				});

		if (result == null || result.size() == 0) {
			return null;
		}
		return result.get(0);
	}
	
	
}
