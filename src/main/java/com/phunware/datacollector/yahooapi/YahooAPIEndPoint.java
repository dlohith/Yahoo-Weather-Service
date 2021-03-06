package com.phunware.datacollector.yahooapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class YahooAPIEndPoint {

	private static final Logger logger = LoggerFactory
			.getLogger(YahooAPIEndPoint.class);
	
	private final static String queryWeatherForcastForWOEID = "select item from weather.forecast where woeid in";
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
		endPoint.append(queryWeatherForcastForWOEID);
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
		logger.info("Calling yahoo end point");
		BufferedReader in = null;
		StringBuffer output = new StringBuffer();
		try{
			URL yahoo = new URL(endPoint);
			logger.info("Endpoint URL :"+ endPoint);
			HttpsURLConnection   connection = (HttpsURLConnection ) yahoo.openConnection();
			connection.setRequestMethod("GET");
			int responseCode = connection.getResponseCode();
			logger.info("response code : "+responseCode);
			in = new BufferedReader(
					new InputStreamReader(
							connection.getInputStream()));

			String line;
			while ((line = in.readLine()) != null){
				output.append(line);
			}

			return output.toString();
		}catch(MalformedURLException e){
			logger.error("Malformed URL : ",e);
		} catch (IOException e) {
			logger.error("IO issue while making http connection : ",e);
		} catch (RuntimeException e){
			logger.error("Unknown issue encountered : ",e);
		}finally{
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					logger.error("IO issue while closing io connection ",e);
				} catch (RuntimeException e){
					logger.error("Unknown issue encountered : ",e);
				}
			}
		}

		return output.toString();
	}
	
	public String getWeatherForecastBasedOnZipCode(String zipCode){
		logger.info("Creating yahoo end point url using zipcode : "+zipCode);
		String endpoint = getYahooAPIEndPoint(zipCode);
		return callEndPoint(endpoint);
	}

}
