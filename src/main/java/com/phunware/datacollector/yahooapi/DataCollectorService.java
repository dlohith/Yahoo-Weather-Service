package com.phunware.datacollector.yahooapi;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phunware.datacollector.Factory.WeatherFactory;
import com.phunware.datacollector.datastore.db.WeatherDBManager;
import com.phunware.datacollector.datastore.db.ZipCodeDBManager;
import com.phunware.datacollector.domain.IConstants;
import com.phunware.datacollector.domain.Weather;
import com.phunware.datacollector.domain.ZipCode;
import com.phunware.datacollector.forecastjson.Forecast;
import com.phunware.datacollector.forecastjson.JSONObjectConverter;

@Service
public class DataCollectorService {


	private static final Logger logger = LoggerFactory
			.getLogger(DataCollectorService.class);
	
	@Autowired
	private WeatherDBManager weatherDBManager;

	@Autowired
	private ZipCodeDBManager zipCodeDBManager;

	@Autowired
	private YahooAPIEndPoint yahooAPIEndPoint;

	@Autowired
	private WeatherFactory weatherFactory;

	public String getWeatherForecat(String zipcode) {

		ZipCode existingZipCode = zipCodeDBManager.getZipCodeByCode(zipcode);

		if (existingZipCode == null) {
			logger.info("Zipcode : "+zipcode +" doesn't exist");
			return getEmptyForecasts();
		}

		logger.info("Zipcode : "+zipcode +" exist");
		
		String forecastJSONString = null;
		Weather weather = weatherDBManager.getWeatherByZipCode(zipcode);
		if (weather == null || !weather.isForecastUptodate()) {
			
			logger.info("Weather forecast for zipcode : "+zipcode +" doesn't exist in db");
			
			String yahooForecast = yahooAPIEndPoint
					.getWeatherForecastBasedOnZipCode(zipcode);
			List<Forecast> forecasts = JSONObjectConverter
					.getForecastListFromYahooJson(yahooForecast);
			Weather newWeather = weatherFactory.createWeatherObject(zipcode,
					forecasts);
			logger.info("Storing weather forecast for zipcode : " + zipcode);
			weatherDBManager.storeWeather(newWeather);

			forecastJSONString = JSONObjectConverter
					.convertForcastListToJsonString(forecasts);
		} else {
			logger.info("Weather forecast for zipcode : "+zipcode +" exists in db");
			List<Forecast> forecasts = weather.getForecasts();
			forecastJSONString = JSONObjectConverter
					.convertForcastListToJsonString(forecasts);
		}
		return forecastJSONString;
	}

	private String getEmptyForecasts() {

		return ((JSONObject) new JSONObject().put(IConstants.FORECASTS,
				new JSONArray())).toJSONString();

	}

}
