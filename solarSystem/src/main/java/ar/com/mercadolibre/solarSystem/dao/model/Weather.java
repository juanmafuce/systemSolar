package ar.com.mercadolibre.solarSystem.dao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Weather implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5107235651547060606L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="WEATHER_ID", nullable = false)
	private int id;

	@Column(name="DAY")
	private int day;

	@Column(name="CONDITION")
	private String condition;
	
	
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
