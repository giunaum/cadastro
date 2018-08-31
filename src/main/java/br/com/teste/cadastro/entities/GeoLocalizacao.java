package br.com.teste.cadastro.entities;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Classe que representa a entidade GeoLocalizacao na tabela.
 */
@Entity
public class GeoLocalizacao implements Entidade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(length = 15)
	private String ipv4;

	@Column(length = 50)
	private String continente;

	@Column(length = 50)
	private String pais;

	@Column(length = 50)
	private String estado;

	@Column(length = 50)
	private String cidade;

	@Column(length = 10)
	private String latitude;

	@Column(length = 10)
	private String longitude;

	@Column
	private Long idRegiao;

	@Column
	private LocalDate dataTemperatura;

	@Column
	private Double temperaturaMinima;

	@Column
	private Double temperaturaMaxima;

	@Override
	public Object getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIpv4() {
		return ipv4;
	}

	public void setIpv4(String ipv4) {
		this.ipv4 = ipv4;
	}

	public String getContinente() {
		return continente;
	}

	public void setContinente(String continente) {
		this.continente = continente;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Long getIdRegiao() {
		return idRegiao;
	}

	public void setIdRegiao(Long idRegiao) {
		this.idRegiao = idRegiao;
	}

	public LocalDate getDataTemperatura() {
		return dataTemperatura;
	}

	public void setDataTemperatura(LocalDate dataTemperatura) {
		this.dataTemperatura = dataTemperatura;
	}

	public Double getTemperaturaMinima() {
		return temperaturaMinima;
	}

	public void setTemperaturaMinima(Double temperaturaMinima) {
		this.temperaturaMinima = temperaturaMinima;
	}

	public Double getTemperaturaMaxima() {
		return temperaturaMaxima;
	}

	public void setTemperaturaMaxima(Double temperaturaMaxima) {
		this.temperaturaMaxima = temperaturaMaxima;
	}
}
