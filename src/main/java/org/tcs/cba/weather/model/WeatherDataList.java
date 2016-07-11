package org.tcs.cba.weather.model;

import java.util.List;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "all"
})
@XmlRootElement
public class WeatherDataList {

	public WeatherDataList() {
		// TODO Auto-generated constructor stub
	}
	
	@JsonProperty("weatherList")
	private List<WeatherData> weatherList;

	/**
     * 
     * @return
     *     The weatherList
     */
    @JsonProperty("weatherList")
	public List<WeatherData> getWeatherList() {
		return weatherList;
	}

    /**
     * 
     * @param
     *     The weatherList
     */
    @JsonProperty("weatherList")
	public void setWeatherList(List<WeatherData> weatherList) {
		this.weatherList = weatherList;
	}
	
    @JsonAnySetter
	public void addWeatherData(WeatherData weatherData) {
		this.weatherList.add(weatherData);
	}
	
	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
	@Override
    public int hashCode() {
        return new HashCodeBuilder().append(weatherList).toHashCode();
    }
	@Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof WeatherData) == false) {
            return false;
        }
        WeatherDataList rhs = ((WeatherDataList) other);
        return new EqualsBuilder().append(weatherList, rhs.weatherList).isEquals();
    }
}
