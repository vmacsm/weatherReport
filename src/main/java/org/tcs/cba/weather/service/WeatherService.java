package org.tcs.cba.weather.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.json.stream.JsonGenerationException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.tcs.cba.weather.bo.ResponseWeatherData;
import org.tcs.cba.weather.exception.DataNotFoundException;
import org.tcs.cba.weather.model.ErrorMessage;
import org.tcs.cba.weather.model.Weather;
/*import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;*/
import org.tcs.cba.weather.model.WeatherData;
import org.tcs.cba.weather.model.WeatherDataList;

public class WeatherService {
	private Client client;
	private WebTarget target;
	public WeatherService() {
		// TODO Auto-generated constructor stub
		init();
	}
	
	final static Logger logger = Logger.getLogger(WeatherService.class);
	protected void init(){
		client = ClientBuilder.newClient();
		target = client.target("http://api.openweathermap.org/data/2.5/weather")
					.queryParam("cnt", "10")
					.queryParam("mode", "json")
					.queryParam("units", "metric")
					.queryParam("appid","b7a2ace7c8829235b419a5ab70d37b4c");
		if(logger.isInfoEnabled())
			logger.info(target);
	}
	
	/**
	 * @return List of ResponseWeatherData object
	 */
	public List<ResponseWeatherData> getAllWeathers(){
			
			WeatherData weatherData = new WeatherData();
			List<ResponseWeatherData> weatherDataList = new ArrayList<ResponseWeatherData>();
			List<String> cityList = new ArrayList<String>();
			
			cityList.add("Chennai,in");
			cityList.add("London,GB");
			cityList.add("Melbourne,AU");
			cityList.add("Adelaide,au");
			cityList.add("Perth,au");
			cityList.add("Bangalore,in");
			cityList.add("Amsterdam,nl");
			cityList.add("Zurich,ch");
			cityList.add("NewYork,us");
			cityList.add("dallas,us");
			cityList.add("riodejaneiro,bl");
			cityList.add("sanfrancisco,us");
			cityList.add("capetown,za");
			
			if(logger.isInfoEnabled())
				logger.info("City list details " +cityList);
			
			for (String city : cityList) {
				weatherData = getForecast(city);
				
				if(logger.isInfoEnabled())
					logger.info("Weather Details from Weather stations " +weatherData);
				ResponseWeatherData responseWeatherData = convertWeatherDataToResponseWeatherData(weatherData);
				responseWeatherData = convertCityCodeToIATA(responseWeatherData);
				
				if(logger.isInfoEnabled())
					logger.info("Response Weather Details " +responseWeatherData);

				weatherDataList.add(responseWeatherData);
			}
			
			return weatherDataList;
	}

	/**
	 * @param WeatherData weather
	 * @return ResponseWeatherData object
	 */
	public ResponseWeatherData getWeather(WeatherData weather){
		weather = getForecast(weather.getName());
		ResponseWeatherData responseWeatherData = convertWeatherDataToResponseWeatherData(weather);
		responseWeatherData = convertCityCodeToIATA(responseWeatherData);
		return responseWeatherData;
		
	}
	
	public WeatherData getForecast(String place) {
		
		if (place == null){
			logger.error("City "+place +" is not available");
			throw new DataNotFoundException("City "+place +" is not available");
		}
		WeatherData weatherData = target.queryParam("q", place)
	            .request(MediaType.APPLICATION_JSON)
	            .get(WeatherData.class);
		if (weatherData == null){
			logger.error("Weather data is not available for the city " + place);
			
			throw new NotFoundException();
			
		}
		return weatherData;
	}
	
	
	
	private ResponseWeatherData convertWeatherDataToResponseWeatherData(WeatherData weatherData) {
		ResponseWeatherData responseWeatherData = new ResponseWeatherData();
		responseWeatherData.setBaseStation(weatherData.getBase());
		responseWeatherData.setCity(weatherData.getName());
		responseWeatherData.setCountry(weatherData.getSys().getCountry());
		responseWeatherData.setHumidity(weatherData.getMain().getHumidity());
		responseWeatherData.setLatitude(weatherData.getCoord().getLat());
		responseWeatherData.setLongitude(weatherData.getCoord().getLon());
		responseWeatherData.setPressure(weatherData.getMain().getPressure());
		responseWeatherData.setTemperature(weatherData.getMain().getTemp());
		responseWeatherData.setMinimumTemperature(weatherData.getMain().getTempMin());
		responseWeatherData.setMaximumTemperature(weatherData.getMain().getTempMax());
		responseWeatherData.setSunrise(formatDate(weatherData.getSys().getSunrise(), 
									responseWeatherData.getCountry(), responseWeatherData.getCity()));
		responseWeatherData.setSunset(formatDate(weatherData.getSys().getSunset(), responseWeatherData.getCountry(), 
									responseWeatherData.getCity()));
		responseWeatherData.setDate(formatDate(weatherData.getDt(), responseWeatherData.getCountry(),
									responseWeatherData.getCity()));
		responseWeatherData.setWind(weatherData.getWind().getSpeed());
		List<Weather> weatherList = weatherData.getWeather();
		if (weatherList != null && weatherList.size() != 0){
			Weather weather = weatherList.get(0);
			responseWeatherData.setCloud(weather.getDescription());
		}
		if(logger.isInfoEnabled())
			logger.info(" responseWeatherData " +responseWeatherData.toString());
		return responseWeatherData;
		
	}

	private String formatDate(Integer timestamp, String country, String city) {

		SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a");
		
		formatter.setTimeZone(TimeZone.getTimeZone(getTimeZone(StringUtils.capitalize(country), city)));
		return formatter.format(new Date((long) timestamp*1000)); // Convert to String first
		
	}
	
	private String getTimeZone(String country, String city){
		Map<String, String> countryMap = new HashMap<String, String>();
		
		countryMap.put("Adelaide", "ACST");
		countryMap.put("Perth", "AWST");
		countryMap.put("Melbourne", "AEST");
		countryMap.put("IN", "IST");
		countryMap.put("GB", "GMT");
		countryMap.put("Dallas", "CDT");
		countryMap.put("New York", "EDT");
		countryMap.put("San Francisco", "PDT");
		countryMap.put("NL", "CEST");
		countryMap.put("CH", "CEST");
		countryMap.put("BR", "BRT");
		countryMap.put("ZA", "SAST"); 
		
		if(country.equals("US") || country.equals("AU")) {
			country = city;
		}
		if(countryMap.containsKey(country)){
			
			return countryMap.get(country);
		}
		return null;
	}
	
	private ResponseWeatherData convertCityCodeToIATA(ResponseWeatherData responseWeatherData){
		Map<String, String> cityMap = new HashMap<String, String>();
		cityMap.put("Chennai", "MAA");
		cityMap.put("London","LON");
		cityMap.put("Melbourne","MEL");
		cityMap.put("Adelaide","ADL");
		cityMap.put("Perth", "PER");
		cityMap.put("Bangalore","BLR");
		cityMap.put("Amsterdam","AMS");
		cityMap.put("Zurich","ZRH");
		cityMap.put("NewYork","NYC");
		cityMap.put("dallas","DAL");
		cityMap.put("riodejaneiro","GIG");
		cityMap.put("San Francisco","SFO");
		cityMap.put("capetown","CPT");
		String city = cityMap.get(responseWeatherData.getCity());
		responseWeatherData.setCity(city);
		return responseWeatherData;
	}
}
