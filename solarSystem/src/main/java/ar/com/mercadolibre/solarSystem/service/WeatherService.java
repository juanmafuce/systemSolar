package ar.com.mercadolibre.solarSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.mercadolibre.solarSystem.dao.model.Weather;
import ar.com.mercadolibre.solarSystem.repository.WeatherRepository;

@Service
public class WeatherService {

	@Autowired
	private WeatherRepository weatherRepository;

	public void save(Weather move) {
		weatherRepository.save(move);

	}

	public Weather getWeatherFromDay(int day) {
		List<Weather> weather = weatherRepository.findByDay(day);
		if (weather != null && weather.get(0) != null) {
			return weather.get(0);
		}
		return null;
	}

}
