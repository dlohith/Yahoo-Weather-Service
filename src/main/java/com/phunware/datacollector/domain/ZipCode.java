package com.phunware.datacollector.domain;

public class ZipCode {

	private String zipCode;
	
	public ZipCode(String zipCode){
		this.zipCode = zipCode;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	@Override
	public boolean equals(Object o) {

		if (o == this) {
			return true;
		}

		if (!(o instanceof ZipCode)) {
			return false;
		}
		 
		ZipCode code = (ZipCode) o;
		 
		return this.zipCode.equals(code.getZipCode());
	}
	
	@Override
	public int hashCode(){
		
		int hashcode = 37;
		hashcode = hashcode * 17 * this.zipCode.hashCode();
		
		return hashcode;
		
	}
	
}
