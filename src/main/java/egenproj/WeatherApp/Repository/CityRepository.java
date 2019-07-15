package egenproj.WeatherApp.Repository;

import java.util.List;

import egenproj.WeatherApp.Entity.City;



public interface CityRepository {
	public List<City> findallCity();
	public City savecity(City city);
	public void update(City city);

}
