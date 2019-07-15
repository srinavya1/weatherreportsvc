package egenproj.WeatherApp.Entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Weather {
	
	
	@Id
	private String id;
	private String humidity;
	private String pressure;
	private String temperature;
	private String city;
	@OneToOne
private Wind wind;
	private String description;
	private String timestamp;
	public String getId() {
		return id;
	}

	public Weather() {
		this.id=UUID.randomUUID().toString();
	}


	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getPressure() {
		return pressure;
	}
	public void setPressure(String preassure) {
		this.pressure = preassure;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	

	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "Weather [id=" + id + ", humidity=" + humidity + ", preassure=" + pressure + ", temperature="
				+ temperature + ", city=" + city + ", wind=" + wind + ", description=" + description + ", timeoftemp="
				+ timestamp + "]";
	}

	
	
	public Wind getWind() {
		return wind;
	}

	public void setWind(Wind wind) {
		this.wind = wind;
	}
	
}
