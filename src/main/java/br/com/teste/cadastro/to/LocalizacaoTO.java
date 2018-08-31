package br.com.teste.cadastro.to;

import java.io.Serializable;

/**
 * Classe que representa a localização pai.
 */
public class LocalizacaoTO implements Serializable {

	private Long distance;
	private String title;
	private String location_type;
	private Long woeid;
	private String latt_long;

	/**
	 * Construtor da classe sem parâmetros.
	 */
	public LocalizacaoTO() {}

	/**
	 * Construtor da classe com parâmetros.
	 *
	 * @param distance
	 * @param title
	 * @param location_type
	 * @param woeid
	 * @param latt_long
	 */
	public LocalizacaoTO(Long distance, String title, String location_type, Long woeid, String latt_long) {
		this.distance = distance;
		this.title = title;
		this.location_type = location_type;
		this.woeid = woeid;
		this.latt_long = latt_long;
	}

	public Long getDistance() {
		return distance;
	}

	public void setDistance(Long distance) {
		this.distance = distance;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation_type() {
		return location_type;
	}

	public void setLocation_type(String location_type) {
		this.location_type = location_type;
	}

	public Long getWoeid() {
		return woeid;
	}

	public void setWoeid(Long woeid) {
		this.woeid = woeid;
	}

	public String getLatt_long() {
		return latt_long;
	}

	public void setLatt_long(String latt_long) {
		this.latt_long = latt_long;
	}
}
