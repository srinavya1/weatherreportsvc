package egenproj.WeatherApp.Service;

import java.util.List;

import egenproj.WeatherApp.Entity.City;
import egenproj.WeatherApp.Entity.Weather;



public interface CityService {
	public List<City> findallCity();
	public City savecity(City city);
	public City ifCityExists(String cityname);
	public void update(City city);
	public List<String> getCityName();
	
}
