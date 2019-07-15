package egenproj.WeatherApp.Entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({@NamedQuery(name="City.finaAll",query="SELECT U FROM City U")
	
	
})
public class City {
	@Id
	private String id;
private String name;
@OneToMany(fetch=FetchType.EAGER)
private List<Weather> weatherreadings;
public List<Weather> getWeatherreadings() {
	return weatherreadings;
}

public void setWeatherreadings(List<Weather> weatherreadings) {
	this.weatherreadings = weatherreadings;
}

/*@OneToMany
private Weather weather;
public Weather getWeather() {
	return weather;
}
*/
/*
public void setWeather(Weather weather) {
	this.weather = weather;
}
*/
public City() {
	
	this.id = UUID.randomUUID().toString();
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

@Override
public String toString() {
	return "City [id=" + id + ", name=" + name + "]";
}
}
