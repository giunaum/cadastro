package br.com.teste.cadastro.business;

import br.com.teste.cadastro.entities.GeoLocalizacao;
import br.com.teste.cadastro.exceptions.DAOException;
import br.com.teste.cadastro.exceptions.GeoLocalizacaoBusinessException;
import br.com.teste.cadastro.persistence.GeoLocalizacaoDAO;
import br.com.teste.cadastro.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe de negócio responsável pelo cadastro da entidade {@link GeoLocalizacao}.
 */
@Service
@Transactional(rollbackFor = GeoLocalizacaoBusinessException.class)
public class GeoLocalizacaoBusiness {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private GeoLocalizacaoDAO geoLocalizacaoDAO;

	/**
	 * Recupera o {@link GeoLocalizacao} conforme o ID fornecido.
	 *
	 * @param id
	 * @return
	 * @throws GeoLocalizacaoBusinessException
	 */
	public GeoLocalizacao getGeoLocalizacaoById(final Integer id) throws GeoLocalizacaoBusinessException {
		if (Util.isEmpty(id)) {
			throw new GeoLocalizacaoBusinessException("O ID não foi informado ou é igual a zero. Impossível prosseguir.");
		}

		GeoLocalizacao geoLocalizacao;

		try {
			geoLocalizacao = geoLocalizacaoDAO.getById(id);
		} catch (DAOException e) {
			String msg = "Erro ao recuperar o geoLocalizacao.";
			logger.error(msg, e);
			throw new GeoLocalizacaoBusinessException(msg, e);
		}

		if (geoLocalizacao == null) {
			throw new GeoLocalizacaoBusinessException("GeoLocalização não encontrado pelo ID.");
		}

		return geoLocalizacao;
	}

	/**
	 * Salva ou atualiza a entidade {@link GeoLocalizacao} conforme os parâmetros fornecidos.
	 *
	 * @param geoLocalizacao
	 * @return
	 * @throws GeoLocalizacaoBusinessException
	 */
	public GeoLocalizacao salvarGeoLocalizacao(final GeoLocalizacao geoLocalizacao) throws GeoLocalizacaoBusinessException {
		if (geoLocalizacao == null) {
			throw new GeoLocalizacaoBusinessException("GeoLocalização não fornecido. Impossível prosseguir");
		}

		GeoLocalizacao geoLocalizacaoPersistido;
		try {
			geoLocalizacaoPersistido = geoLocalizacaoDAO.persistir(geoLocalizacao);
		} catch (DAOException e) {
			String msg = "Erro ao salvar o geoLocalização.";
			logger.error(msg, e);
			throw new GeoLocalizacaoBusinessException(msg, e);
		}

		return geoLocalizacaoPersistido;
	}

	/**
	 * Exclui a entidade {@link GeoLocalizacao}.
	 *
	 * @param geoLocalizacao
	 * @throws GeoLocalizacaoBusinessException
	 */
	public String excluirGeoLocalizacao(final GeoLocalizacao geoLocalizacao) throws GeoLocalizacaoBusinessException {
		if (geoLocalizacao == null) {
			throw new GeoLocalizacaoBusinessException("GeoLocalização não fornecido. Impossível prosseguir");
		}

		try {
			geoLocalizacaoDAO.excluir(geoLocalizacao);
		} catch (DAOException e) {
			String msg = "Erro ao excluir o geoLocalização.";
			logger.error(msg, e);
			throw new GeoLocalizacaoBusinessException(msg, e);
		}

		return "GeoLocalização excluído com sucesso!";
	}
}
