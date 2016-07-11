package org.tcs.cba.weather.service;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.junit.Assert;
import org.junit.Test;
import org.tcs.cba.weather.bo.ResponseWeatherData;
import org.tcs.cba.weather.model.WeatherData;

import junit.framework.TestCase;

public class WeatherServiceTest extends TestCase {
	private Client client;
	private WebTarget target;
	WeatherService weatherService = new WeatherService();
	public WeatherServiceTest() {
		// TODO Auto-generated constructor stub
	}

	public WeatherServiceTest(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void create(){
		client = ClientBuilder.newClient();
		target = client.target("http://api.openweathermap.org/data/2.5/weather")
					.queryParam("cnt", "10")
					.queryParam("mode", "json")
					.queryParam("units", "metric")
					.queryParam("appid","b7a2ace7c8829235b419a5ab70d37b4c");
	}
	
	public void testGetAllWeathers(){
		List<ResponseWeatherData> weatherList = weatherService.getAllWeathers();
		
		for (ResponseWeatherData responseWeatherData : weatherList) {
			Assert.assertNotNull(responseWeatherData);
		}
		
	}
	
	public void testGetWeatherByCity(){
		WeatherData weather = new WeatherData();
		weather.setName("Chennai,in");
		ResponseWeatherData responseWeatherData = weatherService.getWeather(weather);
		Assert.assertNotNull(responseWeatherData);
		
	}
}
