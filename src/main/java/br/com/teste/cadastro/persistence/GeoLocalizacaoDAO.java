package br.com.teste.cadastro.persistence;

import br.com.teste.cadastro.entities.GeoLocalizacao;
import org.springframework.stereotype.Repository;

/**
 * Classe responsável por realizar a persistência de objetos da classe {@link GeoLocalizacao}.
 */
@Repository
public class GeoLocalizacaoDAO extends GenericDAO<GeoLocalizacao> {

	@Override
	protected Class<GeoLocalizacao> getClasseEntidade() {
		return GeoLocalizacao.class;
	}
}
