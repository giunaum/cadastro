package br.com.teste.cadastro.to;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Classe que representa o Cliente.
 */
public class ClienteTO implements Serializable {

	private Integer idCliente;
	private String nomeCliente;
	private Integer idadeCliente;
	private Long idRegiao;
	private String latitude;
	private String longitude;
	private String cidade;
	private LocalDate dataTemperatura;
	private Double temperaturaMinima;
	private Double temperaturaMaxima;

	/**
	 * Contrutor da classe sem parâmetros.
	 */
	public ClienteTO() {}

	/**
	 * Construtor da classe com parâmetros.
	 *
	 * @param idCliente
	 * @param nomeCliente
	 * @param idadeCliente
	 * @param idRegiao
	 * @param latitude
	 * @param longitude
	 * @param cidade
	 * @param dataTemperatura
	 * @param temperaturaMinima
	 * @param temperaturaMaxima
	 */
	public ClienteTO(Integer idCliente, String nomeCliente, Integer idadeCliente, Long idRegiao, String latitude, String longitude, String cidade, LocalDate dataTemperatura, Double temperaturaMinima, Double temperaturaMaxima) {
		this.idCliente = idCliente;
		this.nomeCliente = nomeCliente;
		this.idadeCliente = idadeCliente;
		this.idRegiao = idRegiao;
		this.latitude = latitude;
		this.longitude = longitude;
		this.cidade = cidade;
		this.dataTemperatura = dataTemperatura;
		this.temperaturaMinima = temperaturaMinima;
		this.temperaturaMaxima = temperaturaMaxima;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Integer getIdadeCliente() {
		return idadeCliente;
	}

	public void setIdadeCliente(Integer idadeCliente) {
		this.idadeCliente = idadeCliente;
	}

	public Long getIdRegiao() {
		return idRegiao;
	}

	public void setIdRegiao(Long idRegiao) {
		this.idRegiao = idRegiao;
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

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
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
