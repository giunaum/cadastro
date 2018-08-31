package br.com.teste.cadastro.exceptions;

/**
 * Classe de exceção da classe {@link br.com.teste.cadastro.persistence.DAO}.
 */
public class DAOException extends Exception {

	/**
	 * Construtor da classe de exceção.
	 *
	 * @param message
	 * @param cause
	 */
	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Construtor da classe de exceção.
	 *
	 * @param message
	 */
	public DAOException(String message) {
		super(message);
	}
}
