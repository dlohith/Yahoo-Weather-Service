package com.phunware.ui.weatherforecastsjson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "WeatherForecasts" })
public class WeatherForecasts {

	@JsonProperty("WeatherForecasts")
	private List<WeatherForecast> WeatherForecasts = new ArrayList<WeatherForecast>();
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The WeatherForecasts
	 */
	@JsonProperty("WeatherForecasts")
	public List<WeatherForecast> getWeatherForecasts() {
		return WeatherForecasts;
	}

	/**
	 * 
	 * @param WeatherForecasts
	 *            The WeatherForecasts
	 */
	@JsonProperty("WeatherForecasts")
	public void setWeatherForecasts(List<WeatherForecast> WeatherForecasts) {
		this.WeatherForecasts = WeatherForecasts;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}