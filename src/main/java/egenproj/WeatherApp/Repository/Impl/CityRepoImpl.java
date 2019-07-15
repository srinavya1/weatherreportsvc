package egenproj.WeatherApp.Repository.Impl;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import egenproj.WeatherApp.Entity.City;
import egenproj.WeatherApp.Repository.CityRepository;
@Repository
public class CityRepoImpl implements CityRepository{
@PersistenceContext
private EntityManager em;
	@Override
	public List<City> findallCity() {
		// TODO Auto-generated method stub
		/*List<City> citylist =new ArrayList<City>();
		City c1 =new City ();
		c1.setName("kingsville");
		citylist.add(c1);
		*/
		
		TypedQuery<City> city=em.createNamedQuery("City.finaAll",City.class);
		return city.getResultList();
	}
	
	public City savecity(City city)
	{
		 em.persist(city);
		 return city;
		
	}

	@Override
	public void update(City city) {
		// TODO Auto-generated method stub
		em.merge(city);
	}


	
}
