package egenproj.WeatherApp.Repository;

import egenproj.WeatherApp.Entity.Weather;

public interface WeatherRepository {
	
	public Weather saveWeather(Weather weather);
	public void update(Weather weather);
	
}
