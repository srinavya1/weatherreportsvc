package egenproj.WeatherApp.Facade;

import java.util.List;

import egenproj.WeatherApp.Entity.City;
import egenproj.WeatherApp.Entity.Weather;



public interface CityWeatherFacade {


public Weather addWeatherReading(Weather weather);

Weather getHourlyWeather(String cityname, String dayorhour);

String[] getCitysWeatherAttribute(String cityname, String attribute);

Weather getCitysWeather(String cityname);


//public Weather addWeatherreading ();
}
