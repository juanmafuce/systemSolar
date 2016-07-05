package ar.com.mercadolibre.solarSystem.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ar.com.mercadolibre.solarSystem.dao.model.Weather;
import ar.com.mercadolibre.solarSystem.repository.WeatherRepository;
import ar.com.mercadolibre.solarSystem.service.WeatherService;
import ar.com.mercadolibre.solarSystem.service.response.VulcanoResponse;

@Path("/service")
@Controller
public class SolarSystemController {
	
	@Autowired
	private WeatherRepository weatherRepository;
	

	@Autowired
	private WeatherService weatherService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/vulcanoService")
	public VulcanoResponse vulcanoService(@QueryParam(value = "dia") int day) {
		
		VulcanoResponse vr = new VulcanoResponse();
		Weather weather = weatherService.getWeatherFromDay(day);
		
		if(weather != null) {
			vr.setClima(weather.getCondition());
			vr.setDia(weather.getDay());
		} else {
			vr.setClima("The weather for the day was not found");
			vr.setDia(day);
		}
		
		return vr;
		
	}
	
	
}
