package br.com.teste.cadastro;

import br.com.teste.cadastro.config.MessageCode;
import br.com.teste.cadastro.config.MessageConfig;
import br.com.teste.cadastro.exceptions.ClienteFacadeException;
import br.com.teste.cadastro.facade.ClienteFacade;
import br.com.teste.cadastro.to.ClienteTO;
import br.com.teste.cadastro.util.Util;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(value = MethodSorters.JVM)
public class WsCadastroApplicationTests {

	private static final String REGEX_NUMBER = "[^0-9]";

	@Autowired
	private ClienteFacade clienteFacade;

	@Test
	public void salvarCliente() throws ClienteFacadeException {
		String msg = clienteFacade.salvarCliente("TesteSalvar", 25, "177.92.230.54");
		assertTrue(msg.contains(MessageConfig.getMensagem(MessageCode.SUCESSO_SALVAR_CLIENTE)));
	}

	@Test
	public void alterarliente() throws ClienteFacadeException {
		String msgSalvar = clienteFacade.salvarCliente("TesteAlterar", 27, "177.92.230.54");
		assertTrue(msgSalvar.contains(MessageConfig.getMensagem(MessageCode.SUCESSO_SALVAR_CLIENTE)));

		Integer id = Integer.parseInt(msgSalvar.replaceAll(REGEX_NUMBER, ""));

		String msgAlterar = clienteFacade.atualizarCliente("TesteAlterado", 26, id);
		assertTrue(msgAlterar.contains(MessageConfig.getMensagem(MessageCode.SUCESSO_ALTERAR_CLIENTE)));
	}

	@Test
	public void getClientes() throws ClienteFacadeException {
		List<ClienteTO> clientesTO = clienteFacade.getClientes();
		assertTrue(!Util.isEmpty(clientesTO));
	}

	@Test
	public void getClienteById() throws ClienteFacadeException {
		String msgSalvar = clienteFacade.salvarCliente("TesteConsultar", 28, "177.92.230.54");
		assertTrue(msgSalvar.contains(MessageConfig.getMensagem(MessageCode.SUCESSO_SALVAR_CLIENTE)));

		Integer id = Integer.parseInt(msgSalvar.replaceAll(REGEX_NUMBER, ""));

		ClienteTO clienteTO = clienteFacade.getClienteById(id);
		assertNotNull(clienteTO);
	}

	@Test
	public void excluirCliente() throws ClienteFacadeException {
		String msgSalvar = clienteFacade.salvarCliente("TesteExcluir", 29, "177.92.230.54");
		assertTrue(msgSalvar.contains(MessageConfig.getMensagem(MessageCode.SUCESSO_SALVAR_CLIENTE)));

		Integer id = Integer.parseInt(msgSalvar.replaceAll(REGEX_NUMBER, ""));

		String msgExcluir = clienteFacade.excluirCliente(id);
		assertTrue(msgExcluir.contains(MessageConfig.getMensagem(MessageCode.SUCESSO_EXCLUIR_CLIENTE)));
	}
}