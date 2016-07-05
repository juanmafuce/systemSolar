package ar.com.mercadolibre.solarSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.mercadolibre.solarSystem.dao.model.Weather;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {
	
	
	List<Weather> findByDay(int day);

}
