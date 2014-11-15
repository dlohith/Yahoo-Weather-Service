package com.phunware.webservice.zipcodelistjson;

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
@JsonPropertyOrder({ "zipcode" })
public class Zipcode {

	@JsonProperty("zipcode")
	private String zipcode;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The zipcode
	 */
	@JsonProperty("zipcode")
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * 
	 * @param zipcode
	 *            The zipcode
	 */
	@JsonProperty("zipcode")
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
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