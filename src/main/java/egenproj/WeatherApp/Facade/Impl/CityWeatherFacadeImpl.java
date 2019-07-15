package egenproj.WeatherApp.Facade.Impl;



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

import egenproj.WeatherApp.Entity.City;
import egenproj.WeatherApp.Entity.Weather;
import egenproj.WeatherApp.Entity.Wind;
import egenproj.WeatherApp.Exceptions.BadRequestException;
import egenproj.WeatherApp.Exceptions.NotFoundException;
import egenproj.WeatherApp.Facade.CityWeatherFacade;
import egenproj.WeatherApp.Facade.Constants.Constant;
import egenproj.WeatherApp.Service.CityService;
import egenproj.WeatherApp.Service.WeatherService;
import egenproj.WeatherApp.Service.WindService;
@Service
public class CityWeatherFacadeImpl implements CityWeatherFacade{
private CityService cityservice;
private WeatherService weatherservice;
private WindService windservice;


	public CityWeatherFacadeImpl(CityService cityservice, WeatherService weatherservice, WindService windservice) {
		super();
		this.cityservice = cityservice;
		this.weatherservice = weatherservice;
		this.windservice = windservice;
	}

	@Override
	public Weather addWeatherReading(Weather weather) {
		/* can use j8*/
		if(isValid(weather))
		{
		City currcity=cityservice.ifCityExists(weather.getCity());
		Wind wind=weather.getWind();
					if(wind!=null)
					{
						//System.out.println("wind not null");
					windservice.saveWind(wind);
						//System.out.println(wind.toString()+"is wind");
					
					weather.setWind(wind);
					weatherservice.saveWeather(weather);
					}
		
		
		/*if(currcity==null)
		{
			City newcity=new City();
			newcity.setName(weather.getCity());
			List<Weather> citysweather =new  ArrayList<Weather>();
			citysweather.add(weather);
			newcity.setWeatherreadings(citysweather);
			cityservice.savecity(newcity);
			
		}*//*foreign key */
						if(currcity==null)
						{City newcity =new City();
						System.out.println("printing....");
						newcity.setName(weather.getCity());
							
							cityservice.savecity(newcity);
							
						List<Weather> citysweather =new  ArrayList<Weather>();
							citysweather.add(weather);
							newcity.setWeatherreadings(citysweather);
							System.out.println(newcity.getWeatherreadings().get(0).getCity());
							cityservice.update(newcity);
							return weather;
						}
		System.out.println("cur city not null....");
		System.out.println(currcity.toString());
			List<Weather> weatherreading=currcity.getWeatherreadings();
			
			weatherreading.add(weather);
	currcity.setWeatherreadings(weatherreading);
	//System.out.println(currcity.getWeatherreadings().get(0).getCity()+"   is city");*/
		cityservice.update(currcity);
			
		
		// TODO Auto-generated method stub
		return weather;
		}
		
		throw new BadRequestException("bad weather reading");
	}

	
	


@Override
public Weather getCitysWeather(String cityname) {
	// TODO Auto-generated method stub
	City currcity=cityservice.ifCityExists(cityname);
	if(currcity!=null){
		System.out.println("cityfound");
	List<Weather> citysweatherlist =currcity.getWeatherreadings();
	Weather latestweather=findlatest(citysweatherlist);
	return latestweather;
	}
	else{
	throw new NotFoundException("City name entered ("+cityname+") does not match with the existind cities in the repositoty ")	;
    }
	/*System.out.println("city not found");
	return new Weather();*/
}





@Override
public String[] getCitysWeatherAttribute(String cityname, String attribute) {
	// TODO Auto-generated method stub
	String[] retattribute=new String[2];
	retattribute[0]=cityname+"s "+attribute+"  value";
	Weather citysweather=getCitysWeather( cityname);
	if(citysweather!=null)
	{
	if(attribute.equalsIgnoreCase(Constant.WIND))
	{
		retattribute[1]=	citysweather.getWind().toString();
	}
	else if(attribute.equalsIgnoreCase(Constant.DESCIPTION))
	{
		
	}
	else if(attribute.equalsIgnoreCase(Constant.HUMIDITY))
	{
		retattribute[1]=	citysweather.getHumidity();
	}
	else if(attribute.equalsIgnoreCase(Constant.PREASSURE))
	{
		retattribute[1]=	citysweather.getPressure();
	}
	else if(attribute.equalsIgnoreCase(Constant.TEMPTATURE))
	{
		retattribute[1]=	citysweather.getTemperature();
	}
	else if(attribute.equalsIgnoreCase(Constant.WIND))
	{
		retattribute[1]=	citysweather.getWind().getSpeed();
	}
	else if(attribute.equalsIgnoreCase(Constant.DEGREE))
	{
		retattribute[1]=	citysweather.getWind().getDegree();
	}
	else if(attribute.equalsIgnoreCase(Constant.TIMESTAMP))
	{
		retattribute[1]=	citysweather.getTimestamp();
	}
	else 
	{
		throw new NotFoundException("Attribute  name entered ("+attribute+") does not match with the existind attribute in the repositoty ")	;
	}
	return retattribute;
	
	}else{throw new NotFoundException("City name entered ("+cityname+") does not match with the existind cities in the repositoty ")	;}
	
}

@Override
public Weather getHourlyWeather(String cityname, String dayorhour) {
	// TODO Auto-generated method stub
	if(!(dayorhour.equalsIgnoreCase(Constant.DAY)||dayorhour.equalsIgnoreCase(Constant.HOUR)))
		throw new NotFoundException("The value entered ("+ dayorhour +") is invalid please enter hour/day after city name ");
	
	City currcity=cityservice.ifCityExists(cityname);
	if(currcity!=null)
	{
		
		List<Weather> citysweather=currcity.getWeatherreadings();
		return getAverageWeather( partition(citysweather,dayorhour));
	}
	
	
	return null;
}



//**********************************************Helpermethords********************************************/
public boolean isValid(Weather weather)
{

	try{
		Double.parseDouble(weather.getHumidity());
		Double.parseDouble(weather.getPressure());
		Double.parseDouble(weather.getTemperature());
		Double.parseDouble(weather.getWind().getDegree());
		Double.parseDouble(weather.getWind().getSpeed());
		Constant.TIME_STAMP_DATE_FORMAT.parse(weather.getTimestamp().replaceAll(Constant.ALPHABET_REGEX, ""));
			
		
	}
	catch(Exception e)
	{
		System.out.println("exception caught "+e.getMessage());
		e.printStackTrace();
		return false;
		
	}
	
return true;	
}

public Weather findlatest(List<Weather> weatherlist)
{
	List<Weather> w1=sort(weatherlist);

	
	return sort(weatherlist).get(weatherlist.size()-1) ;
	
}
public List<Weather> partition(List<Weather> weatherlist,String dayorhour)
{   
	Date datetocompare=getDatetocompare(dayorhour);
	List<Weather> collect = weatherlist.stream().filter(w->{
		try {
			return Constant.TIME_STAMP_DATE_FORMAT.parse(w.getTimestamp().replaceAll(Constant.ALPHABET_REGEX, "")).compareTo(datetocompare)>0;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}).collect(Collectors.toList());
	
	return collect;
	
	
}

public Date getDatetocompare(String dayorhour)
{
	
	if(dayorhour.equalsIgnoreCase(Constant.DAY)){
		Date dateWithoutTime=null;
			
			try {
				 dateWithoutTime = Constant.DATE_WITHOUT_TIME_SIME_DATE_FORMAT.parse(Constant.DATE_WITHOUT_TIME_SIME_DATE_FORMAT.format(new Date()));
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return dateWithoutTime;	
		
	}
	else{
	
		//int num_of_minitutes_in_hour=60;
		Calendar date = Calendar.getInstance();
		long t= date.getTimeInMillis();
		Date date60minback=new Date(t - (Constant.MILLISECONDS_IN_AN_HOUR));
		return date60minback;
	}	
	
}

public Weather getAverageWeather(List<Weather> weatherlist)
{
	Weather avgweather=new Weather();
	if(!weatherlist.isEmpty()){
	avgweather.setCity(weatherlist.get(0).getCity());
	Wind  avgwind=new Wind();
	avgwind.setSpeed(weatherlist.stream().mapToDouble(w->Double.valueOf(w.getWind().getSpeed())).average().toString());
	avgwind.setDegree(weatherlist.stream().mapToDouble(w->Double.valueOf(w.getWind().getDegree())).average().toString());
	avgweather.setWind(avgwind);
	avgweather.setHumidity(weatherlist.stream().mapToDouble(w->Double.valueOf(w.getHumidity())).average().toString());
	avgweather.setPressure(weatherlist.stream().mapToDouble(w->Double.valueOf(w.getPressure())).average().toString());
	avgweather.setTemperature(weatherlist.stream().mapToDouble(w->Double.valueOf(w.getTemperature())).average().toString());
	}
	return avgweather;
}

public List<Weather> sort (List<Weather> weatherlist)
{
	
/*	Collections.sort(weatherlist,(a,b)->{
		try {
			return format1.parse(a.getTimeoftemp().replaceAll("[a-z A-Z]", "")).compareTo(format1.parse(b.getTimeoftemp().replaceAll("[a-z A-Z]", "")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	});*/
	List<Weather> collect = weatherlist.stream().sorted(
			(w1,w2)->{
				try {
					return Constant.TIME_STAMP_DATE_FORMAT.parse(w1.getTimestamp().replaceAll(Constant.ALPHABET_REGEX, "")).
					compareTo(Constant.TIME_STAMP_DATE_FORMAT.parse(w2.getTimestamp().replaceAll(Constant.ALPHABET_REGEX, "")));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return 0;
			}
			).collect(Collectors.toList());
					
	
	return collect;
}

	

	


	/***************************************old replaced by java8*************************************************************//*
	
	public List<Weather> partition(List<Weather> weatherlist,String dayorhour)
	{ long ONE_MINUTE_IN_MILLIS=60000;//millisecs

	Calendar date = Calendar.getInstance();
	long t= date.getTimeInMillis();
	Date s60minback=new Date(t - (60 * ONE_MINUTE_IN_MILLIS));
	DateFormat format1 = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss.SSS", Locale.ENGLISH);
		List<Weather> topartition=sort(weatherlist,dayorhour);
		List<Weather> partioned=new ArrayList();
		if(dayorhour.equalsIgnoreCase("hour"))
		{
			for(Weather w : topartition)
			{
				try {
					if( format1.parse(w.getTimeoftemp().replaceAll("[a-z A-Z]", "")).compareTo(s60minback)>0)
					{
						partioned.add(w);
						
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
		}
		return partioned;
	}
	
	public List<Weather> sort (List<Weather> weatherlist,String dayorhour)
	{
		// s4=s4.replaceAll("[a-z A-Z]", "");
	        DateFormat format1 = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss.SSS", Locale.ENGLISH);
		Collections.sort(weatherlist,(a,b)->{
			try {
				return format1.parse(a.getTimeoftemp().replaceAll("[a-z A-Z]", "")).compareTo(format1.parse(b.getTimeoftemp().replaceAll("[a-z A-Z]", "")));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		});
		
		
		return weatherlist;
	}*/
	
}
