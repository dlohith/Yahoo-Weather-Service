package com.phunware.ui.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class ZipcodeCityMapper {

	private Map<String,String> zipcodeCityMap;
	
	@PostConstruct
	public void init(){
		zipcodeCityMap = new HashMap<String, String>();
		zipcodeCityMap.put("73301","Austin");
		zipcodeCityMap.put("75014","Irving");
		zipcodeCityMap.put("75023","Plano");
		zipcodeCityMap.put("75029","Lewisville");
		zipcodeCityMap.put("75083","Richardson");
		zipcodeCityMap.put("75210","Dallas");
		zipcodeCityMap.put("75551","Atlanta");
		zipcodeCityMap.put("78202","San Antonio");
		zipcodeCityMap.put("76001","Arlington");
		zipcodeCityMap.put("77701","Beaumont");
		zipcodeCityMap.put("75601","Longview");
		
	}
	
	public String getCity(String zipcode){
		return zipcodeCityMap.get(zipcode);
	}
	
}
