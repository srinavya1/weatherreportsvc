package egenproj.WeatherApp.Service.Impl;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egenproj.WeatherApp.Entity.Wind;
import egenproj.WeatherApp.Repository.WindRepository;
import egenproj.WeatherApp.Service.WindService;
@Service
public class WindServiceImpl implements WindService {
	
private WindRepository windrepository;
	public WindServiceImpl(WindRepository windrepository) {
	//super();
	this.windrepository = windrepository;
}
	@Override
	@Transactional
	public Wind saveWind(Wind wind) {
		// TODO Auto-generated method stub
		return windrepository.saveWind(wind);
	}

}
