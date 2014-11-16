package com.phunware.webservice.datacollector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phunware.webservice.domain.WeatherForecast;
import com.phunware.webservice.forecast.WeatherForecastFactory;
import com.phunware.webservice.forecastjson.Forecast;
import com.phunware.webservice.forecastjson.ForecastsObject;
import com.phunware.webservice.zipcodelistjson.Zipcode;

@Service
public class ForecastData {

	private static final Logger logger = LoggerFactory
			.getLogger(ForecastData.class);

	@Autowired
	@Qualifier("datacollectorendpoint")
	private String datacollectorendpoint;

	@Autowired
	private WeatherForecastFactory weatherForecastFactory;


	
	public String getWeatherForecasts(List<Zipcode> zipcodes){
		String weatherForecastListJson = null;
		
		List<WeatherForecast> weatherForecasts = getWeatherForecast(zipcodes);
		
		weatherForecastListJson = JSONObjectConverter.convertWeatherForecastListToJSON(weatherForecasts);
		
		return weatherForecastListJson;
	}
	
	
	
	
	private List<WeatherForecast> getWeatherForecast(List<Zipcode> zipcodes) {

		List<WeatherForecast> weatherForecasts = new ArrayList<WeatherForecast>();
		if (zipcodes != null) {
			for (Zipcode zipcode : zipcodes) {
				String forecastForZipcode = getForecastForZipCode(zipcode
						.getZipcode());
				WeatherForecast weatherForecast = weatherForecastFactory
						.createWeatherObject(zipcode.getZipcode(),
								getForecasts(forecastForZipcode));
				weatherForecasts.add(weatherForecast);
			}
		}

		return weatherForecasts;
	}

	private List<Forecast> getForecasts(String forecastForZipcode) {
		return convertJSONStringToForecast(forecastForZipcode).getForecasts();
	}

	private static ForecastsObject convertJSONStringToForecast(String json) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			ForecastsObject forecastsObject = objectMapper.readValue(json,
					ForecastsObject.class);
			return forecastsObject;
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

	private String getForecastForZipCode(String zipCode) {
		String endPoint = datacollectorendpoint + zipCode;
		String output = callEndPoint(endPoint);

		return output;
	}

	private String callEndPoint(String endPoint) {
		logger.info("Calling Data Collector end point");
		BufferedReader in = null;
		StringBuffer output = new StringBuffer();
		try {
			URL dataCollector = new URL(endPoint);
			HttpURLConnection connection = (HttpURLConnection) dataCollector
					.openConnection();
			connection.setRequestMethod("GET");
			int responseCode = connection.getResponseCode();
			logger.info("response code : " + responseCode);
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));

			String line;
			while ((line = in.readLine()) != null) {
				output.append(line);
			}

			return output.toString();
		} catch (MalformedURLException e) {
			logger.error("Malformed URL : ", e);
		} catch (IOException e) {
			logger.error("IO issue while making http connection : ", e);
		} catch (RuntimeException e) {
			logger.error("Unknown issue encountered : ", e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("IO issue while closing io connection ", e);
				} catch (RuntimeException e) {
					logger.error("Unknown issue encountered : ", e);
				}
			}
		}

		return output.toString();
	}
}
