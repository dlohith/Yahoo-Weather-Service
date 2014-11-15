package com.phunware.webservice.forecast;

import org.springframework.stereotype.Service;

import com.phunware.webservice.domain.ZipCode;


@Service
public class ZipCodeFactory {

	public ZipCode createZipCode(String zipCode){
		return new ZipCode(zipCode);
	}
}
