package ar.com.mercadolibre.solarSystem.service.response;

import java.io.Serializable;

public class VulcanoResponse implements Serializable {
	
	private static final long serialVersionUID = -1239843799934164473L;

	private int dia;
	
	private String clima;

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}


}
