package br.com.teste.cadastro.exceptions;

import br.com.teste.cadastro.config.MessageCode;
import br.com.teste.cadastro.config.MessageConfig;
import br.com.teste.cadastro.util.Util;

import java.io.Serializable;
import java.util.List;

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

	/**
	 * Construtor da classe de exceção.
	 *
	 * @param code
	 */
	public ClienteBusinessException(MessageCode code) {
		super(MessageConfig.getMensagem(code));
	}

	/**
	 * Construtor da classe de exceção.
	 *
	 * @param message
	 * @param parametros
	 */
	public ClienteBusinessException(String message, List<String> parametros) {
		super(Util.formatarString(Boolean.TRUE, message, (String[]) parametros.toArray()));
	}

	/**
	 * Construtor da classe de exceção.
	 *
	 * @param message
	 * @param parametros
	 */
	public ClienteBusinessException(String message, String... parametros) {
		super(Util.formatarString(Boolean.TRUE, message, parametros));
	}

	/**
	 * Construtor da classe de exceção.
	 *
	 * @param code
	 * @param parametros
	 */
	public ClienteBusinessException(MessageCode code, List<String> parametros) {
		super(Util.formatarString(Boolean.TRUE, MessageConfig.getMensagem(code), (String[]) parametros.toArray()));
	}

	/**
	 * Construtor da classe de exceção.
	 *
	 * @param code
	 * @param parametros
	 */
	public ClienteBusinessException(MessageCode code, String... parametros) {
		super(Util.formatarString(Boolean.TRUE, MessageConfig.getMensagem(code), parametros));
	}
}
