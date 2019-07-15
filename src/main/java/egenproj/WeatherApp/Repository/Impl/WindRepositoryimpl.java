package egenproj.WeatherApp.Repository.Impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import egenproj.WeatherApp.Entity.Wind;
import egenproj.WeatherApp.Repository.WindRepository;
@Repository
public class WindRepositoryimpl implements WindRepository {
	@PersistenceContext
	private EntityManager em;
	@Override
	public Wind saveWind(Wind wind) {
		// TODO Auto-generated method stub
		em.persist(wind);
		return wind;
	}

}
