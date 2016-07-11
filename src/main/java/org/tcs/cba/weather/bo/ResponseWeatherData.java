package org.tcs.cba.weather.bo;


import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.tcs.cba.weather.model.Weather;


@XmlRootElement
public class ResponseWeatherData {

	public ResponseWeatherData() {
		// TODO Auto-generated constructor stub
	}
	
	private Double longitude;
	private Double latitude;
	private String cloud;
	private Double temperature;
    private Integer pressure;
    private Integer humidity;
    private Integer minimumTemperature;
    private Double maximumTemperature;
    private String baseStation;
    private Double wind;
    private String city;
    private String country;
    private String sunrise;
    private String sunset;
    private String date;
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public String getCloud() {
		return cloud;
	}
	public void setCloud(String cloud) {
		this.cloud = cloud;
	}
	public Double getTemperature() {
		return temperature;
	}
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	public Integer getPressure() {
		return pressure;
	}
	public void setPressure(Integer pressure) {
		this.pressure = pressure;
	}
	public Integer getHumidity() {
		return humidity;
	}
	public void setHumidity(Integer humidity) {
		this.humidity = humidity;
	}
	public Integer getMinimumTemperature() {
		return minimumTemperature;
	}
	public void setMinimumTemperature(Integer minimumTemperature) {
		this.minimumTemperature = minimumTemperature;
	}
	public Double getMaximumTemperature() {
		return maximumTemperature;
	}
	public void setMaximumTemperature(Double maximumTemperature) {
		this.maximumTemperature = maximumTemperature;
	}
	public String getBaseStation() {
		return baseStation;
	}
	public void setBaseStation(String baseStation) {
		this.baseStation = baseStation;
	}
	public Double getWind() {
		return wind;
	}
	public void setWind(Double wind) {
		this.wind = wind;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getSunrise() {
		return sunrise;
	}
	public void setSunrise(String sunrise) {
		this.sunrise = sunrise;
	}
	public String getSunset() {
		return sunset;
	}
	public void setSunset(String sunset) {
		this.sunset = sunset;
	}
    @Override
    public int hashCode() {
    	return new HashCodeBuilder().append(longitude).append(latitude).append(baseStation)
    			.append(city).append(country).append(cloud).append(humidity)
    			.append(temperature).append(minimumTemperature)
    			.append(maximumTemperature).append(pressure)
    			.append(wind).append(sunrise).append(sunset).toHashCode();
    }
    @Override
    public boolean equals(Object other) {
    	if (other == this) {
            return true;
        }
        if ((other instanceof Weather) == false) {
            return false;
        }
        ResponseWeatherData rhs = ((ResponseWeatherData) other);
        return new EqualsBuilder().append(longitude, rhs.longitude)
        		.append(latitude, rhs.latitude).append(baseStation, rhs.baseStation)
        		.append(city, rhs.city).append(country, rhs.country)
        		.append(cloud,  rhs.cloud).append(humidity, rhs.humidity)
        		.append(temperature, rhs.temperature).append(minimumTemperature, rhs.minimumTemperature)
        		.append(maximumTemperature, rhs.maximumTemperature)
        		.append(pressure, rhs.pressure).append(wind, rhs.wind)
        		.append(sunrise, rhs.sunrise).append(sunset, rhs.sunset).isEquals();
    }
    
    @Override
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
