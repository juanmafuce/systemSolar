package ar.com.mercadolibre.solarSystem.model;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Planet {
	
	private double velocity;
	
	private double direction;
	
	private double distanceToSun;
	
	private Point2D point;
	
	public Planet(double velocity, double direction, double distanceToSun) {
		this.velocity = velocity;
		this.direction = direction;
		this.distanceToSun = distanceToSun;
		point = new Double();
	}
	
	
	protected void move(int day){
		
		BigDecimal x = new BigDecimal(Math.sin(Math.toRadians(day * (this.velocity * direction)))* this.distanceToSun);
		x.setScale(4, RoundingMode.HALF_UP);
		BigDecimal y = new BigDecimal(Math.cos(Math.toRadians(day * (this.velocity * direction)))* this.distanceToSun);
		y.setScale(4, RoundingMode.HALF_UP);
		point.setLocation(x.doubleValue(),y.doubleValue());
				
	}
	
	public Point2D getPoint() {
		return point;
	}


	public void setPoint(Point2D point) {
		this.point = point;
	}
	
	

}
