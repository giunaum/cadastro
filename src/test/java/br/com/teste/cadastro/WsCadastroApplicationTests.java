package br.com.teste.cadastro;

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

	private static final Integer ID = 2;

	@Autowired
	private ClienteFacade clienteFacade;

	@Test
	public void salvarCliente() throws ClienteFacadeException {
		String msg = clienteFacade.salvarCliente("Teste", 25, "177.92.230.54");
		assertTrue(msg.contains("Cliente salvo com sucesso!"));
	}

	@Test
	public void alterarliente() throws ClienteFacadeException {
		String msg = clienteFacade.atualizarCliente("TesteAlterado", 26, ID);
		assertTrue(msg.contains("Cliente atualizado com sucesso!"));
	}

	@Test
	public void getClientes() throws ClienteFacadeException {
		List<ClienteTO> clientesTO = clienteFacade.getClientes();
		assertTrue(!Util.isEmpty(clientesTO));
	}

	@Test
	public void getClienteById() throws ClienteFacadeException {
		ClienteTO clienteTO = clienteFacade.getClienteById(ID);
		assertNotNull(clienteTO);
	}

	@Test
	public void excluirCliente() throws ClienteFacadeException {
		String msg = clienteFacade.excluirCliente(ID);
		assertTrue(msg.contains("Cliente excluído com sucesso!"));
	}
}