package br.com.teste.cadastro.business;

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
			String msg = "Erro ao recuperar os clientes.";
			logger.error(msg, e);
			throw new ClienteBusinessException(msg, e);
		}

		if (Util.isEmpty(clientes)) {
			throw new ClienteBusinessException("Os Clientes não foram encontrados.");
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
			String msg = "Erro ao recuperar o cliente.";
			logger.error(msg, e);
			throw new ClienteBusinessException(msg, e);
		}

		if (cliente == null) {
			throw new ClienteBusinessException("Cliente não encontrado pelo ID.");
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
			String mensagem = "Os seguintes parametros obrigatórios não foram preenchidos: {0}.";
			throw new ClienteBusinessException(Util.formatarString(true, mensagem, (String[]) parametros.toArray()));
		}

		boolean isPersistido = !Util.isEmpty(id) && id > BigInteger.ZERO.intValue();
		Cliente cliente = getClientePreenchido(id, nome, idade, geoLocalizacao, isPersistido);
		Cliente clientePersistido;

		try {
			clientePersistido = clienteDAO.persistir(cliente);
		} catch (DAOException e) {
			String msg = isPersistido ? "Erro ao atualizar o cliente." : "Erro ao salvar o cliente.";
			logger.error(msg, e);
			throw new ClienteBusinessException(msg, e);
		}

		StringBuilder mensagem = new StringBuilder();
		if (clientePersistido == null) {
			throw new ClienteBusinessException(isPersistido ? "Cliente não atualizado." : "Cliente não salvo.");
		} else {
			mensagem.append(isPersistido ? "Cliente atualizado com sucesso! [ID: " : "Cliente salvo com sucesso! [ID: ");
			mensagem.append(cliente.getId().toString()).append("]");
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
			throw new ClienteBusinessException("Cliente não fornecido. Impossível prosseguir");
		}

		try {
			clienteDAO.excluir(cliente);
		} catch (DAOException e) {
			String msg = "Erro ao excluir o cliente.";
			logger.error(msg, e);
			throw new ClienteBusinessException(msg, e);
		}

		return "Cliente excluído com sucesso!";
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
