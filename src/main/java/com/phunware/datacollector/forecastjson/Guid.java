package com.phunware.datacollector.forecastjson;

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
@JsonPropertyOrder({ "isPermaLink", "content" })
public class Guid {

	@JsonProperty("isPermaLink")
	private String isPermaLink;
	@JsonProperty("content")
	private String content;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The isPermaLink
	 */
	@JsonProperty("isPermaLink")
	public String getIsPermaLink() {
		return isPermaLink;
	}

	/**
	 * 
	 * @param isPermaLink
	 *            The isPermaLink
	 */
	@JsonProperty("isPermaLink")
	public void setIsPermaLink(String isPermaLink) {
		this.isPermaLink = isPermaLink;
	}

	/**
	 * 
	 * @return The content
	 */
	@JsonProperty("content")
	public String getContent() {
		return content;
	}

	/**
	 * 
	 * @param content
	 *            The content
	 */
	@JsonProperty("content")
	public void setContent(String content) {
		this.content = content;
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