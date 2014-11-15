package com.phunware.datacollector.forecastjson;

import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phunware.datacollector.domain.IConstants;

public class JSONObjectConverter {

	private static final Logger logger = LoggerFactory
			.getLogger(JSONObjectConverter.class);

	private static YahooAPIOutput convertJSONStringToForecast(String json) {

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			YahooAPIOutput yahooAPIOutput = objectMapper.readValue(json,
					YahooAPIOutput.class);
			return yahooAPIOutput;
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

	private static List<Forecast> parseYahooAPIOutput(
			YahooAPIOutput yahooAPIOutput) {
		Query query = yahooAPIOutput.getQuery();
		if (query != null) {
			Results results = query.getResults();
			if (results != null) {
				Channel channel = results.getChannel();
				if (channel != null) {
					Item item = channel.getItem();
					if (item != null) {
						return item.getForecast();
					}
				}
			}
		}
		return null;
	}

	public static String convertForcastListToJsonString(
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
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(IConstants.FORECASTS, jsonArray);

		return jsonObject.toJSONString();
	}
	

	private static JSONObject parseJsonStringToJsonObject(String json) {
		try {
			return (JSONObject) new JSONParser().parse(json);
		} catch (ParseException e) {
			logger.error("Parse exception : ", e);
		}

		return null;
	}

	private static JSONObject convertForcastToJsonString(Forecast forecast) {
		JSONObject forecastJSONObject = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String forecastString = objectMapper.writeValueAsString(forecast);
			forecastJSONObject = parseJsonStringToJsonObject(forecastString);
		} catch (JsonGenerationException e) {
			logger.error("JSON generation error : ", e);
			;
		} catch (JsonMappingException e) {
			logger.error("JSON mapping error : ", e);
		} catch (IOException e) {
			logger.error("JSON unknown error : ", e);
		} catch (RuntimeException e) {
			logger.error("Runtime unknown error : ", e);
		}
		return forecastJSONObject;
	}

	public static String getForecastsJSON(String json) {
		YahooAPIOutput yahooAPIOutput = convertJSONStringToForecast(json);

		List<Forecast> forecasts = parseYahooAPIOutput(yahooAPIOutput);
		String forecastsJSON = convertForcastListToJsonString(forecasts);

		return forecastsJSON;

	}
	
	public static List<Forecast> getForecastListFromYahooJson(String json){
		YahooAPIOutput yahooAPIOutput = convertJSONStringToForecast(json);

		return parseYahooAPIOutput(yahooAPIOutput);
	}

}
