package br.com.teste.cadastro.controller;

import br.com.teste.cadastro.exceptions.ClienteFacadeException;
import br.com.teste.cadastro.facade.ClienteFacade;
import br.com.teste.cadastro.to.ClienteTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * Classe de controle responsável pela manipulação das informações da entidade {@link ClienteTO}.
 */
@Controller
@RestController
@RequestMapping(value = "/cliente")
public class ClienteController implements Serializable {

	@Autowired
	private ClienteFacade clientebusiness;

	/**
	 * Recupera os clientes.
	 *
	 * @return
	 * @throws ClienteFacadeException
	 */
	@GetMapping(value = "/listar")
	@ResponseBody
	public List<ClienteTO> getClientes() throws ClienteFacadeException {
		return clientebusiness.getClientes();
	}

	/**
	 * Recupera o {@link ClienteTO} conforme o ID fornecido.
	 *
	 * @param id
	 * @return
	 * @throws ClienteFacadeException
	 */
	@GetMapping(value = "/obter/{id}")
	@ResponseBody
	public ClienteTO getClienteById(@PathVariable Integer id) throws ClienteFacadeException {
		return clientebusiness.getClienteById(id);
	}

	/**
	 * Salva a entidade {@link ClienteTO} conforme os parâmetros fornecidos.
	 *
	 * @param nome
	 * @param idade
	 * @param request
	 * @throws ClienteFacadeException
	 */
	@GetMapping(value = "/salvar/{nome}/{idade}")
	@ResponseBody
	public String salvarCliente(@PathVariable String nome, @PathVariable Integer idade, HttpServletRequest request) throws ClienteFacadeException {
		return clientebusiness.salvarCliente(nome, idade, request.getRemoteAddr());
	}

	/**
	 * Salva a entidade {@link ClienteTO} conforme os parâmetros fornecidos.
	 *
	 * @param nome
	 * @param idade
	 * @param id
	 * @throws ClienteFacadeException
	 * @return
	 */
	@GetMapping(value = "/atualizar/{nome}/{idade}/{id}")
	@ResponseBody
	public String alterarliente(@PathVariable String nome, @PathVariable Integer idade, @PathVariable Integer id) throws ClienteFacadeException {
		return clientebusiness.atualizarCliente(nome, idade, id);
	}

	/**
	 * Salva a entidade {@link ClienteTO}.
	 *
	 * @param id
	 * @throws ClienteFacadeException
	 */
	@GetMapping(value = "/excluir/{id}")
	@ResponseBody
	public String excluirCliente(@PathVariable Integer id) throws ClienteFacadeException {
		return clientebusiness.excluirCliente(id);
	}
}
