package br.com.teste.cadastro.exceptions;

import java.io.Serializable;

/**
 * Classe de exceção da classe {@link br.com.teste.cadastro.business.GeoLocalizacaoBusiness}.
 */
public class GeoLocalizacaoBusinessException extends Exception implements Serializable {

	/**
	 * Construtor da classe de exceção.
	 *
	 * @param message
	 * @param cause
	 */
	public GeoLocalizacaoBusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Construtor da classe de exceção.
	 *
	 * @param message
	 */
	public GeoLocalizacaoBusinessException(String message) {
		super(message);
	}
}
