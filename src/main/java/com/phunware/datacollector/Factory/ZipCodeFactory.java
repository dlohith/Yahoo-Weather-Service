package com.phunware.datacollector.Factory;

import org.springframework.stereotype.Service;

import com.phunware.datacollector.domain.ZipCode;


@Service
public class ZipCodeFactory {

	public ZipCode createZipCode(String zipCode){
		return new ZipCode(zipCode);
	}
}
