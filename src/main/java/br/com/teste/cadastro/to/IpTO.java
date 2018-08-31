package br.com.teste.cadastro.to;

import java.io.Serializable;

/**
 * Classe que representa o IP.
 */
public class IpTO implements Serializable {

	private String status;
	private IpDataTO data;

	/**
	 * Construtor da classe sem parâmetros.
	 */
	public IpTO() {}

	/**
	 * Construtor da classe com parâmetros.
	 *
	 * @param status
	 * @param data
	 */
	public IpTO(String status, IpDataTO data) {
		this.status = status;
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public IpDataTO getData() {
		return data;
	}

	public void setData(IpDataTO data) {
		this.data = data;
	}
}
