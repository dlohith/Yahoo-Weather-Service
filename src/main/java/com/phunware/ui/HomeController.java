package com.phunware.ui;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.phunware.ui.service.ForecastData;
import com.phunware.ui.service.ZipcodeCityMapper;
import com.phunware.ui.weatherforecastsjson.Forecast;
import com.phunware.ui.weatherforecastsjson.JSONObjectConverter;
import com.phunware.ui.weatherforecastsjson.WeatherForecast;
import com.phunware.ui.weatherforecastsjson.WeatherForecasts;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private ForecastData forecastData;
	
	@Autowired
	private ZipcodeCityMapper zipcodeCityMapper;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		return "home";
	}
	
	
	@RequestMapping(value = "/getforecast", method = RequestMethod.GET)
	public String getForecast(HttpServletRequest req, Model model) {
		String zipcode = req.getParameter("zipcode");
		
		if(zipcode == null ){
			logger.error("Selected zipcode is empty");
			return "home";
		}
		logger.info("Selected zipcode in UI : "+zipcode);
		logger.info("Selected city in UI : "+zipcodeCityMapper.getCity(zipcode));
		String weatherForecastJSON = forecastData.getForecastForZipCode(zipcode);
		logger.info("Forecast output "+weatherForecastJSON);
		
		WeatherForecasts weatherForecasts = JSONObjectConverter.convertJSONStringToForecast(weatherForecastJSON);
		
		if(weatherForecasts != null){
			List<WeatherForecast> weatherForecastList = weatherForecasts.getWeatherForecasts();
			if(weatherForecastList != null && weatherForecastList.size() > 0){
				List<Forecast> forecastList = weatherForecastList.get(0).getForecasts();
				model.addAttribute("forecastList", forecastList);
				model.addAttribute("city", zipcodeCityMapper.getCity(zipcode));
			}
		}
		
		return "home";
	}
	
	
}
