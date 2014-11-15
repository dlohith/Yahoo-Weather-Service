package com.phunware.webservice.datacollector;

import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phunware.webservice.domain.WeatherForecast;
import com.phunware.webservice.forecastjson.Forecast;
import com.sun.istack.internal.NotNull;

public class JSONObjectConverter {
	
	private static final String WEATHERFORECASTS = "WeatherForecasts";
	private static final String FORECASTS = "Forecasts";
	private static final String ZIPCODE = "zipcode";

	private static final Logger logger = LoggerFactory
			.getLogger(JSONObjectConverter.class);
	
	public static String convertWeatherForecastListToJSON(List<WeatherForecast> weatherForecasts){
		JSONArray jsonArray = new JSONArray();
		if(weatherForecasts != null){
			for(WeatherForecast weatherForecast : weatherForecasts){
				if(weatherForecast != null){
					JSONObject weatherForecastJSON = convertWeatherForecastToJSON(weatherForecast);
					jsonArray.add(weatherForecastJSON);
				}
			}
		}
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put(WEATHERFORECASTS, jsonArray);
		
		return jsonObject.toJSONString();
	}
	
	
	private static @NotNull JSONObject convertWeatherForecastToJSON(WeatherForecast weatherForecast){
		
		JSONArray forecastsJSONArray = convertForcastListToJsonString(weatherForecast.getForecasts());
		JSONObject weatherForecastJSON = new JSONObject();
		weatherForecastJSON.put(ZIPCODE, weatherForecast.getZipCode().getZipCode());
		weatherForecastJSON.put(FORECASTS,forecastsJSONArray);
		return weatherForecastJSON;
	}
	
	private static JSONArray convertForcastListToJsonString(
			List<Forecast> forecasts) {
		JSONArray jsonArray = new JSONArray();
		if (forecasts != null) {
			for (Forecast forecast : forecasts) {
				JSONObject forecastJSONObject = convertForcastToJsonString(forecast);
				if (forecastJSONObject != null) {
					jsonArray.add(forecastJSONObject);
				}
			}
		}


		return jsonArray;
	}
	
	private static JSONObject convertForcastToJsonString(Forecast forecast) {
		JSONObject forecastJSONObject = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String forecastString = objectMapper.writeValueAsString(forecast);
			forecastJSONObject = parseJsonStringToJsonObject(forecastString);
		} catch (JsonGenerationException e) {
			logger.error("JSON generation error : ", e);
		} catch (JsonMappingException e) {
			logger.error("JSON mapping error : ", e);
		} catch (IOException e) {
			logger.error("JSON unknown error : ", e);
		} catch (RuntimeException e) {
			logger.error("Runtime unknown error : ", e);
		}
		return forecastJSONObject;
	}
	
	private static JSONObject parseJsonStringToJsonObject(String json) {
		try {
			return (JSONObject) new JSONParser().parse(json);
		} catch (ParseException e) {
			logger.error("Parse exception : ", e);
		}

		return null;
	}
	
	public static String emptyResponse(){
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(WEATHERFORECASTS, jsonArray);
		
		return jsonObject.toJSONString();
	}
}
