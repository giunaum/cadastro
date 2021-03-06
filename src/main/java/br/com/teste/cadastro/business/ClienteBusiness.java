package br.com.teste.cadastro.business;

import br.com.teste.cadastro.config.MessageCode;
import br.com.teste.cadastro.config.MessageConfig;
import br.com.teste.cadastro.entities.Cliente;
import br.com.teste.cadastro.entities.GeoLocalizacao;
import br.com.teste.cadastro.exceptions.ClienteBusinessException;
import br.com.teste.cadastro.exceptions.DAOException;
import br.com.teste.cadastro.persistence.ClienteDAO;
import br.com.teste.cadastro.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de negócio responsável pelo cadastro da entidade {@link Cliente}.
 */
@Service
@Transactional(rollbackFor = ClienteBusinessException.class)
public class ClienteBusiness {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ClienteDAO clienteDAO;

	/**
	 * Recupera os clientes.
	 *
	 * @return
	 * @throws ClienteBusinessException
	 */
	public List<Cliente> getClientes() throws ClienteBusinessException {
		List<Cliente> clientes = new ArrayList<>();
		try {
			clientes.addAll(clienteDAO.getAll());
		} catch (DAOException e) {
			String msg = MessageConfig.getMensagem(MessageCode.FALHA_RECUPERAR_CLIENTES);
			logger.error(msg, e);
			throw new ClienteBusinessException(msg, e);
		}

		if (Util.isEmpty(clientes)) {
			throw new ClienteBusinessException(MessageCode.CLIENTES_NAO_ENCONTRADOS);
		}

		return clientes;
	}

	/**
	 * Recupera o {@link Cliente} conforme o ID fornecido.
	 *
	 * @param id
	 * @return
	 * @throws ClienteBusinessException
	 */
	public Cliente getClienteById(final Integer id) throws ClienteBusinessException {
		if (Util.isEmpty(id)) {
			throw new ClienteBusinessException("O ID não foi informado ou é igual a zero. Impossível prosseguir.");
		}

		Cliente cliente;

		try {
			cliente = clienteDAO.getById(id);
		} catch (DAOException e) {
			String msg = MessageConfig.getMensagem(MessageCode.FALHA_RECUPERAR_CLIENTE);
			logger.error(msg, e);
			throw new ClienteBusinessException(msg, e);
		}

		if (cliente == null) {
			throw new ClienteBusinessException(MessageCode.CLIENTE_NAO_ENCONTRADO);
		}

		return cliente;
	}

	/**
	 * Salva a entidade {@link Cliente} conforme os parâmetros fornecidos.
	 *
	 * @param nome
	 * @param idade
	 * @return
	 * @throws ClienteBusinessException
	 */
	public String salvarCliente(final String nome, final Integer idade, final GeoLocalizacao geoLocalizacao) throws ClienteBusinessException {
		return salvarOuAtualizarCliente(nome, idade, -BigInteger.ONE.intValue(), geoLocalizacao);
	}

	/**
	 * Atualiza a entidade {@link Cliente} conforme os parâmetros fornecidos.
	 *
	 * @param nome
	 * @param idade
	 * @param id
	 * @return
	 * @throws ClienteBusinessException
	 */
	public String atualizarCliente(final String nome, final Integer idade, final Integer id) throws ClienteBusinessException {
		return salvarOuAtualizarCliente(nome, idade, id, null);
	}

	/**
	 * Salva ou atualiza a entidade {@link Cliente} conforme os parâmetros fornecidos.
	 *
	 * @param nome
	 * @param idade
	 * @param id
	 * @param geoLocalizacao
	 * @return
	 * @throws ClienteBusinessException
	 */
	public String salvarOuAtualizarCliente(final String nome, final Integer idade, final Integer id, final GeoLocalizacao geoLocalizacao) throws ClienteBusinessException {
		List<String> parametros = new ArrayList<>();

		if (Util.isEmpty(id)) {
			parametros.add("ID");
		}

		if (Util.isBlank(nome)) {
			parametros.add("Nome");
		}

		if (idade == null) {
			parametros.add("Idade");
		}

		if (!Util.isEmpty(parametros)) {
			throw new ClienteBusinessException(MessageCode.PARAMETROS_OBRIGATORIOS, parametros);
		}

		boolean isPersistido = !Util.isEmpty(id) && id > BigInteger.ZERO.intValue();
		Cliente cliente = getClientePreenchido(id, nome, idade, geoLocalizacao, isPersistido);
		Cliente clientePersistido;

		try {
			clientePersistido = clienteDAO.persistir(cliente);
		} catch (DAOException e) {
			String msg = MessageConfig.getMensagem(isPersistido ? MessageCode.FALHA_ALTERAR_CLIENTE : MessageCode.FALHA_SALVAR_CLIENTE);
			logger.error(msg, e);
			throw new ClienteBusinessException(msg, e);
		}

		StringBuilder mensagem = new StringBuilder();
		if (clientePersistido == null) {
			throw new ClienteBusinessException(isPersistido ? MessageCode.CLIENTE_NAO_ATUALIZADO : MessageCode.CLIENTE_NAO_SALVO);
		} else {
			mensagem.append(MessageConfig.getMensagem(isPersistido ? MessageCode.SUCESSO_ALTERAR_CLIENTE : MessageCode.SUCESSO_SALVAR_CLIENTE));
			mensagem.append(" [ID: ").append(cliente.getId().toString()).append("]");
		}

		return mensagem.toString();
	}

	/**
	 * Exclui a entidade {@link Cliente}.
	 *
	 * @param cliente
	 * @throws ClienteBusinessException
	 */
	public String excluirCliente(final Cliente cliente) throws ClienteBusinessException {
		if (cliente == null) {
			throw new ClienteBusinessException(MessageCode.CLIENTE_NAO_FORNECIDO);
		}

		try {
			clienteDAO.excluir(cliente);
		} catch (DAOException e) {
			String msg = MessageConfig.getMensagem(MessageCode.FALHA_EXCLUIR_CLIENTE);
			logger.error(msg, e);
			throw new ClienteBusinessException(msg, e);
		}

		return MessageConfig.getMensagem(MessageCode.SUCESSO_EXCLUIR_CLIENTE);
	}

	/**
	 * Retorna o {@link Cliente} preenchido conforme os parâmetros informados.
	 *
	 * @param id
	 * @param nome
	 * @param idade
	 * @param geoLocalizacao
	 * @param isPersistido
	 * @return
	 * @throws ClienteBusinessException
	 */
	private Cliente getClientePreenchido(final Integer id, final String nome, final Integer idade, final GeoLocalizacao geoLocalizacao, final boolean isPersistido) throws ClienteBusinessException {
		Cliente cliente = isPersistido ? getClienteById(id) : new Cliente();

		if (isPersistido) {
			cliente.setId(id);
		} else {
			cliente.setGeoLocalizacao(geoLocalizacao);
		}

		cliente.setNome(nome);
		cliente.setIdade(idade);

		return cliente;
	}
}
