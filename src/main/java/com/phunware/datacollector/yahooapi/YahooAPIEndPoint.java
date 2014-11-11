package com.phunware.datacollector.yahooapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class YahooAPIEndPoint {

	private final static String queryWeatherForcast = "select item from weather.forecast where woeid in";
	private final static String queryWoeid = "select woeid from geo.places(1)";

	private final static String queryWhere = "where text =";
	private final static String AND = "&";
	private final static String jsonFormat = "format=json";
	private final static String OPENPARA = "(";
	private final static String CLOSEPARA = ")";
	private final static String DOUBLEQU_STRING ="\"";
	private final static String yahooWeatherAPIURL = "https://query.yahooapis.com/v1/public/yql?q=";

	private String getYahooAPIEndPoint(String zipCode){
		StringBuffer endPoint = new StringBuffer(yahooWeatherAPIURL);
		endPoint.append(queryWeatherForcast);
		endPoint.append(OPENPARA);
		endPoint.append(queryWoeid);
		endPoint.append(queryWhere);
		endPoint.append(DOUBLEQU_STRING);
		endPoint.append(zipCode);
		endPoint.append(DOUBLEQU_STRING);
		endPoint.append(CLOSEPARA);
		endPoint.append(AND);
		endPoint.append(jsonFormat);

		return endPoint.toString().replace(" ", "%20");
	}

	private String callEndPoint(String endPoint){
		BufferedReader in = null;
		StringBuffer output = new StringBuffer();
		try{
			URL yahoo = new URL(endPoint);
			HttpsURLConnection   connection = (HttpsURLConnection ) yahoo.openConnection();
			connection.setRequestMethod("GET");
			int responseCode = connection.getResponseCode();
			System.out.println("response code : "+responseCode);
			in = new BufferedReader(
					new InputStreamReader(
							connection.getInputStream()));

			String line;
			while ((line = in.readLine()) != null){
				output.append(line);
			}

			return output.toString();
		}catch(MalformedURLException e){
			System.err.println("Malformed URL : "+e.getMessage());
		} catch (IOException e) {
			System.err.println("IO issue while making http connection : "+e.getMessage());
		} catch (RuntimeException e){
			System.err.println("Unknown issue encountered : "+e.getMessage());
		}finally{
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {

				} catch (RuntimeException e){

				}
			}
		}

		return output.toString();
	}
	
	public String getWeatherForecastBasedOnZipCode(String zipCode){
		String endpoint = getYahooAPIEndPoint(zipCode);
		return callEndPoint(endpoint);
	}

	public static void main(String args[]) {
		YahooAPIEndPoint yahooAPIEndPoint = new YahooAPIEndPoint();

		System.out.println(yahooAPIEndPoint.getWeatherForecastBasedOnZipCode("85281"));

	}
}
