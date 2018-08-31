package br.com.teste.cadastro.exceptions;

import java.io.Serializable;

/**
 * Classe de exceção da classe {@link br.com.teste.cadastro.facade.ClienteFacade}.
 */
public class ClienteFacadeException extends Exception implements Serializable {

	/**
	 * Construtor da classe de exceção.
	 *
	 * @param message
	 * @param cause
	 */
	public ClienteFacadeException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Construtor da classe de exceção.
	 *
	 * @param message
	 */
	public ClienteFacadeException(String message) {
		super(message);
	}
}
