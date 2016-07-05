package ar.com.mercadolibre.solarSystem;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import ar.com.mercadolibre.solarSystem.constants.PlanetConstants;
import ar.com.mercadolibre.solarSystem.constants.WeatherConstants;
import ar.com.mercadolibre.solarSystem.dao.model.Weather;
import ar.com.mercadolibre.solarSystem.model.Betasoide;
import ar.com.mercadolibre.solarSystem.model.Ferengi;
import ar.com.mercadolibre.solarSystem.model.Planet;
import ar.com.mercadolibre.solarSystem.model.SolarSystem;
import ar.com.mercadolibre.solarSystem.model.Vulcano;
import ar.com.mercadolibre.solarSystem.repository.WeatherRepository;
import ar.com.mercadolibre.solarSystem.service.WeatherService;

@Configuration
public class App {
	private static final int DAYS = 3600;

	@Autowired
	private WeatherService weatherService;

	public void init() {

		List<Planet> planets = new ArrayList<Planet>();

		Planet farengui = new Ferengi(1, PlanetConstants.HORARIO, 500);
		Planet betasoide = new Betasoide(3, PlanetConstants.HORARIO, 2000);
		Planet vulcano = new Vulcano(5, PlanetConstants.ANTI_HORARIO, 1000);

		planets.add(farengui);
		planets.add(betasoide);
		planets.add(vulcano);

		SolarSystem system = new SolarSystem(planets);

		for (int i = 1; i < DAYS + 1; i++) {

			weatherService.save(system.move(i));
		}

		// busco al máximo y le seteo el pico de lluvia.
		Weather weather = weatherService.getWeatherFromDay(system.getDayAreaMax());
		weather.setCondition(WeatherConstants.RAIN_MAX);

		weatherService.save(weather);

	}
}
