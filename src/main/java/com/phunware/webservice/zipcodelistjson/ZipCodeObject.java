package com.phunware.webservice.zipcodelistjson;

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
@JsonPropertyOrder({ "zipcodes" })
public class ZipCodeObject {

	@JsonProperty("zipcodes")
	private List<Zipcode> zipcodes = new ArrayList<Zipcode>();
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The zipcodes
	 */
	@JsonProperty("zipcodes")
	public List<Zipcode> getZipcodes() {
		return zipcodes;
	}

	/**
	 * 
	 * @param zipcodes
	 *            The zipcodes
	 */
	@JsonProperty("zipcodes")
	public void setZipcodes(List<Zipcode> zipcodes) {
		this.zipcodes = zipcodes;
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