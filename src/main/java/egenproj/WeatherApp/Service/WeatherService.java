package egenproj.WeatherApp.Service;

import egenproj.WeatherApp.Entity.Weather;

public interface WeatherService {
	
	public void update(Weather weather);


	public Weather saveWeather(Weather weather);
	

}
