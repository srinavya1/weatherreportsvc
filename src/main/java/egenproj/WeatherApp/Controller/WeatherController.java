package egenproj.WeatherApp.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import egenproj.WeatherApp.Controller.Constants.Mapper;
import egenproj.WeatherApp.Entity.Weather;
import egenproj.WeatherApp.Facade.CityWeatherFacade;
import egenproj.WeatherApp.Service.WeatherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@RestController
@RequestMapping(value=Mapper.WEATHER)
//@CrossOrigin(origins = "http://mocker.egen.io/" )
public class WeatherController {

	
	private CityWeatherFacade cityweatherfacade;
	private WeatherService weatherservice;
	public WeatherController(CityWeatherFacade cityweatherfacade, WeatherService weatherservice) {
	
		this.cityweatherfacade = cityweatherfacade;
		this.weatherservice = weatherservice;
	}
	
	
	@ApiOperation(value = "Find citys weather", notes = "Returns the citys latest weather reading")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	@RequestMapping(method = RequestMethod.GET,value="{city}")
	public Weather getCitysWeather(@PathVariable("city") String Cityname)
	{
		return cityweatherfacade.getCitysWeather(Cityname);
		
	}
	
	@ApiOperation(value = "Find one weather attribute of a city", notes = "Returns a single attribute of weather reading eg(temperature ,humidity ) from the latest weather reading of the specified city")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	@RequestMapping(method = RequestMethod.GET,value="{city}/{attribute}")
	public String[] getCitysWeatherAttribute(@PathVariable("city") String Cityname,@PathVariable("attribute") String attribute)
	{
		return cityweatherfacade.getCitysWeatherAttribute(Cityname,attribute);
		
	}
	
	@ApiOperation(value = "Post Weather", notes = "Adds a weather reading to an exiting city if city exists else creates a new city reading and adds the weather object")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			
			@ApiResponse(code = 500, message = "Internal Server Error"), })
@RequestMapping(method = RequestMethod.POST)
	public Weather getCitysWeatherParameter(@RequestBody Weather weather)
	{
		
		return cityweatherfacade.addWeatherReading(weather);
	}

	@ApiOperation(value = "Get Avg Weather ", notes = "Get daily /hourly avaerage of a citys weather ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	@RequestMapping(method = RequestMethod.GET,value="getavg/{city}/{dayorhr}")
	public Weather getHourlyWeather(@PathVariable("city") String cityname,@PathVariable("dayorhr") String dayorhour )
	{
		return cityweatherfacade.getHourlyWeather(cityname,dayorhour);
	}
	
	
}
