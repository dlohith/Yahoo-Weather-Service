package com.phunware.datacollector.forecastjson;

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
@JsonPropertyOrder({ "title", "lat", "long", "link", "pubDate", "condition",
		"description", "forecast", "guid" })
public class Item {

	@JsonProperty("title")
	private String title;
	@JsonProperty("lat")
	private String lat;
	@JsonProperty("long")
	private String _long;
	@JsonProperty("link")
	private String link;
	@JsonProperty("pubDate")
	private String pubDate;
	@JsonProperty("condition")
	private Condition condition;
	@JsonProperty("description")
	private String description;
	@JsonProperty("forecast")
	private List<Forecast> forecast = new ArrayList<Forecast>();
	@JsonProperty("guid")
	private Guid guid;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The title
	 */
	@JsonProperty("title")
	public String getTitle() {
		return title;
	}

	/**
	 * 
	 * @param title
	 *            The title
	 */
	@JsonProperty("title")
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 
	 * @return The lat
	 */
	@JsonProperty("lat")
	public String getLat() {
		return lat;
	}

	/**
	 * 
	 * @param lat
	 *            The lat
	 */
	@JsonProperty("lat")
	public void setLat(String lat) {
		this.lat = lat;
	}

	/**
	 * 
	 * @return The _long
	 */
	@JsonProperty("long")
	public String getLong() {
		return _long;
	}

	/**
	 * 
	 * @param _long
	 *            The long
	 */
	@JsonProperty("long")
	public void setLong(String _long) {
		this._long = _long;
	}

	/**
	 * 
	 * @return The link
	 */
	@JsonProperty("link")
	public String getLink() {
		return link;
	}

	/**
	 * 
	 * @param link
	 *            The link
	 */
	@JsonProperty("link")
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * 
	 * @return The pubDate
	 */
	@JsonProperty("pubDate")
	public String getPubDate() {
		return pubDate;
	}

	/**
	 * 
	 * @param pubDate
	 *            The pubDate
	 */
	@JsonProperty("pubDate")
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	/**
	 * 
	 * @return The condition
	 */
	@JsonProperty("condition")
	public Condition getCondition() {
		return condition;
	}

	/**
	 * 
	 * @param condition
	 *            The condition
	 */
	@JsonProperty("condition")
	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	/**
	 * 
	 * @return The description
	 */
	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description
	 *            The description
	 */
	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return The forecast
	 */
	@JsonProperty("forecast")
	public List<Forecast> getForecast() {
		return forecast;
	}

	/**
	 * 
	 * @param forecast
	 *            The forecast
	 */
	@JsonProperty("forecast")
	public void setForecast(List<Forecast> forecast) {
		this.forecast = forecast;
	}

	/**
	 * 
	 * @return The guid
	 */
	@JsonProperty("guid")
	public Guid getGuid() {
		return guid;
	}

	/**
	 * 
	 * @param guid
	 *            The guid
	 */
	@JsonProperty("guid")
	public void setGuid(Guid guid) {
		this.guid = guid;
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