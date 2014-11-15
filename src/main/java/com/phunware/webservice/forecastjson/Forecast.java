package com.phunware.webservice.forecastjson;

import java.util.HashMap;
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
@JsonPropertyOrder({ "text", "high", "day", "code", "low", "date" })
public class Forecast {

	@JsonProperty("text")
	private String text;
	@JsonProperty("high")
	private String high;
	@JsonProperty("day")
	private String day;
	@JsonProperty("code")
	private String code;
	@JsonProperty("low")
	private String low;
	@JsonProperty("date")
	private String date;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The text
	 */
	@JsonProperty("text")
	public String getText() {
		return text;
	}

	/**
	 * 
	 * @param text
	 *            The text
	 */
	@JsonProperty("text")
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * 
	 * @return The high
	 */
	@JsonProperty("high")
	public String getHigh() {
		return high;
	}

	/**
	 * 
	 * @param high
	 *            The high
	 */
	@JsonProperty("high")
	public void setHigh(String high) {
		this.high = high;
	}

	/**
	 * 
	 * @return The day
	 */
	@JsonProperty("day")
	public String getDay() {
		return day;
	}

	/**
	 * 
	 * @param day
	 *            The day
	 */
	@JsonProperty("day")
	public void setDay(String day) {
		this.day = day;
	}

	/**
	 * 
	 * @return The code
	 */
	@JsonProperty("code")
	public String getCode() {
		return code;
	}

	/**
	 * 
	 * @param code
	 *            The code
	 */
	@JsonProperty("code")
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 
	 * @return The low
	 */
	@JsonProperty("low")
	public String getLow() {
		return low;
	}

	/**
	 * 
	 * @param low
	 *            The low
	 */
	@JsonProperty("low")
	public void setLow(String low) {
		this.low = low;
	}

	/**
	 * 
	 * @return The date
	 */
	@JsonProperty("date")
	public String getDate() {
		return date;
	}

	/**
	 * 
	 * @param date
	 *            The date
	 */
	@JsonProperty("date")
	public void setDate(String date) {
		this.date = date;
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