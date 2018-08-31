package br.com.teste.cadastro.persistence;

import br.com.teste.cadastro.entities.Cliente;
import org.springframework.stereotype.Repository;

/**
 * Classe responsável por realizar a persistência de objetos da classe {@link Cliente}.
 */
@Repository
public class ClienteDAO extends GenericDAO<Cliente> {

	@Override
	protected Class<Cliente> getClasseEntidade() {
		return Cliente.class;
	}
}
