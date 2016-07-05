package ar.com.mercadolibre.solarSystem.model;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import ar.com.mercadolibre.solarSystem.constants.WeatherConstants;
import ar.com.mercadolibre.solarSystem.dao.model.Weather;


public class SolarSystem {
	
	private List<Planet> planets;
	
	private Point2D pointSun;
	
	private double areaMax = 0;
	
	private int dayAreaMax = 0;
	
	public SolarSystem(List<Planet> planets) {
		this.planets = planets;
		pointSun = new Double(0,0);
	}
	
	
	public Weather move(int days) {
		
		Weather weather = new Weather();
		
		//seteo el día
		weather.setDay(days);
		
		//Hago mover a los planetas
		for (Planet planet : planets) {
			planet.move(days);
		}
		
		//obtengo el area del triangulo
		double areaOfTriangle = getAreaOfTriangle(planets.get(0).getPoint(),planets.get(1).getPoint(),planets.get(2).getPoint());
		
		//si el área es 0, estan alineados
		if (areaOfTriangle < 0.0001) {
//				me fijo si esta alineado con el sol, para esto con dos puntos me alcanza
			if(planetsIsAlineatedWithSun(planets.get(0).getPoint(),planets.get(1).getPoint())){
				weather.setCondition(WeatherConstants.DROUGHT);
			} else {
				weather.setCondition(WeatherConstants.PRESSURE_AND_TEMPERATURE_GOOD);
			}
			//Si el area no es 0 quiere decir que forma un triangulo, de este modo busco si el sol esta dentro del triangulo
		} else if(pointInTriangle(pointSun, planets.get(0).getPoint(), planets.get(1).getPoint(), planets.get(2).getPoint())){
			weather.setCondition(WeatherConstants.RAIN);
			//si no está el sol dentro..
		} else {
			weather.setCondition(WeatherConstants.CLEAR);
		}
		//guardo el dia del area maximo, sirve para encontrar el pico de intensidad
		if(areaOfTriangle > areaMax) {
			setDayAreaMax(weather.getDay());
			areaMax = areaOfTriangle;
		}
		
		return weather;
		
	}
	
	private double sign(Point2D p1, Point2D p2, Point2D p3) {
		return (p1.getX() - p3.getX()) * (p2.getY() - p3.getY())
				- (p2.getX() - p3.getX()) * (p1.getY() - p3.getY());
	}

	private boolean pointInTriangle(Point2D pt, Point2D v1, Point2D v2, Point2D v3) {
		boolean b1, b2, b3;

		b1 = sign(pt, v1, v2) < 0.0f;
		b2 = sign(pt, v2, v3) < 0.0f;
		b3 = sign(pt, v3, v1) < 0.0f;

		return ((b1 == b2) && (b2 == b3));
	}
	
	/**
	 * Metodo encargado de devolver el area de un triangulo
	 * @param point1
	 * @param point2
	 * @param point3
	 * @return
	 */
	private double getAreaOfTriangle(Point2D point1,Point2D point2,Point2D point3) {
		double area =  Math.abs((point1.getX()-point3.getX())*(point2.getY()-point1.getY())-
		         (point1.getX()-point2.getX())*(point3.getY()-point1.getY()))*0.5;
		
		//lo convierto a bigDecimal para obtener un double de dos posiciones
		BigDecimal bd = new BigDecimal(area);
	    bd = bd.setScale(4, RoundingMode.HALF_UP);
	    
	    return bd.doubleValue();
	}


	private boolean planetsIsAlineatedWithSun(Point2D point1,Point2D point2) {
		//me fijo si la comprobacion funciona, esto quiere decir que la recta pasa por el punto (0;0)
		BigDecimal linealOne = new BigDecimal((pointSun.getY()-point1.getY())/(point2.getY()-point1.getY()));
		BigDecimal linealTwo = new BigDecimal((pointSun.getX()-point1.getX())/(point2.getX()-point1.getX()));
				
		linealOne = linealOne.setScale(4, RoundingMode.HALF_UP);
		linealTwo = linealTwo.setScale(4, RoundingMode.HALF_UP);
		
		return linealOne.compareTo(linealTwo) == 0;
	}

	@Deprecated
	private boolean sunInsideOfTriangle(List<Planet> planets, double areaOfPlanets) {
		double areaOne = getAreaOfTriangle(planets.get(0).getPoint(), planets.get(1).getPoint(), pointSun);
		double areaTwo = getAreaOfTriangle(planets.get(0).getPoint(), planets.get(2).getPoint(), pointSun);
		double areaThree = getAreaOfTriangle(planets.get(2).getPoint(), planets.get(1).getPoint(), pointSun);
		
		//como es el modulo en caso de que sea negativo lo multiplico por -1
		if ((areaOne < 0 ? areaOne * -1 : areaOne) + (areaTwo < 0 ? areaTwo * -1
				: areaTwo) + (areaThree < 0 ? areaThree * -1
						: areaThree) == areaOfPlanets) {
			return true;
		}
		
		return false;
		
	}
	
	public int getDayAreaMax() {
		return dayAreaMax;
	}


	public void setDayAreaMax(int dayAreaMax) {
		this.dayAreaMax = dayAreaMax;
	}

}
