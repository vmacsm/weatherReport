package org.tcs.cba.weather.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.tcs.cba.weather.bo.ResponseWeatherData;
import org.tcs.cba.weather.model.WeatherData;
import org.tcs.cba.weather.service.WeatherService;

@Path("/weathers")
public class WeatherResource {
	public final static MediaType TEXT_CSV_TYPE = new MediaType("text", "csv");
	public WeatherResource() {
		// TODO Auto-generated constructor stub
	}
	
WeatherService weatherService = new WeatherService();
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.TEXT_PLAIN)
	public List<ResponseWeatherData> getWeathers(){
		return weatherService.getAllWeathers();
	}
	
	@GET
	@Path("/{cityId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseWeatherData getWeather(@PathParam("cityId") String cityId){
		WeatherData weather = new WeatherData();
		weather.setName(cityId);
		return weatherService.getWeather(weather);
		
	}

}
