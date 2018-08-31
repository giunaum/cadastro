package br.com.teste.cadastro.exceptions;

import java.io.Serializable;

/**
 * Classe de exceção da classe {@link br.com.teste.cadastro.business.ClienteBusiness}.
 */
public class ClienteBusinessException extends Exception implements Serializable {

	/**
	 * Construtor da classe de exceção.
	 *
	 * @param message
	 * @param cause
	 */
	public ClienteBusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Construtor da classe de exceção.
	 *
	 * @param message
	 */
	public ClienteBusinessException(String message) {
		super(message);
	}
}
