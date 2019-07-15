package egenproj.WeatherApp.Service.Impl;
import org.springframework.transaction.annotation.Transactional;

import egenproj.WeatherApp.Entity.City;
import egenproj.WeatherApp.Entity.Weather;
import egenproj.WeatherApp.Entity.Wind;
import egenproj.WeatherApp.Repository.CityRepository;
import egenproj.WeatherApp.Service.CityService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
@Service
public class CityServiceImpl implements CityService {
private CityRepository cityrepository;

public CityServiceImpl(CityRepository cityrepository) {

	this.cityrepository = cityrepository;
}
@Transactional
@Override
public List<City> findallCity() {
	// TODO Auto-generated method stub
	return cityrepository.findallCity();
}



@Transactional
@Override
public City savecity(City city) {
	// TODO Auto-generated method stub
	return cityrepository.savecity(city);
}



@Override
public City ifCityExists(String cityname) {

		List<City> allcity=findallCity();
		for(City c1 : allcity)
		{
		if(	c1.getName().equalsIgnoreCase(cityname))
		{
		return c1;	
		}
		/*can use j8*/
		}
		System.out.println("no matching city");
		return null;
	}



@Transactional
@Override
public void update(City city) {
	// TODO Auto-generated method stub
	cityrepository.update(city);
	
}
@Transactional(readOnly=true)



@Override
public List<String> getCityName() {
	
	/*can use j8*/
	List<City> allcity=cityrepository.findallCity();
	List<String> citynames=new ArrayList<>();
	for(City city :allcity )
	{
		citynames.add(city.getName());
		
	}
	return citynames;
	
}



	

}
