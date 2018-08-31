package br.com.teste.cadastro.facade;

import br.com.teste.cadastro.business.ClienteBusiness;
import br.com.teste.cadastro.business.GeoLocalizacaoBusiness;
import br.com.teste.cadastro.entities.Cliente;
import br.com.teste.cadastro.entities.GeoLocalizacao;
import br.com.teste.cadastro.exceptions.ClienteBusinessException;
import br.com.teste.cadastro.exceptions.ClienteFacadeException;
import br.com.teste.cadastro.exceptions.GeoLocalizacaoBusinessException;
import br.com.teste.cadastro.to.*;
import br.com.teste.cadastro.util.Util;
import br.com.teste.cadastro.wsclient.IpVigilanteClient;
import br.com.teste.cadastro.wsclient.MetaWeatherClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Classe facade da entidade {@link br.com.teste.cadastro.entities.Cliente}
 */
@Component
@Transactional(rollbackFor = ClienteFacadeException.class)
public class ClienteFacade {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ClienteBusiness clienteBusiness;

	@Autowired
	private GeoLocalizacaoBusiness geoLocalizacaoBusiness;

	@Autowired
	private IpVigilanteClient ipVigilanteClient;

	@Autowired
	private MetaWeatherClient metaWeatherClient;


	/**
	 * Recupera a lista de {@link ClienteTO}.
	 *
	 * @return
	 * @throws ClienteFacadeException
	 */
	public List<ClienteTO> getClientes() throws ClienteFacadeException {
		try {
			List<Cliente> clientes = clienteBusiness.getClientes();
			return getClientesTO(clientes);
		} catch (ClienteBusinessException e) {
			String msg = e.getMessage();
			logger.error(msg, e);
			throw new ClienteFacadeException(msg, e);
		}
	}

	/**
	 * Recupera o {@link ClienteTO} conforme o ID fornecido.
	 *
	 * @param id
	 * @return
	 * @throws ClienteFacadeException
	 */
	public ClienteTO getClienteById(final Integer id) throws ClienteFacadeException {
		try {
			Cliente cliente = clienteBusiness.getClienteById(id);
			return getClienteTO(cliente);
		} catch (ClienteBusinessException e) {
			String msg = e.getMessage();
			logger.error(msg, e);
			throw new ClienteFacadeException(msg, e);
		}
	}

	/**
	 * Salva a entidade {@link Cliente} conforme os parâmetros fornecidos.
	 *
	 * @param nome
	 * @param idade
	 * @param remoteAddr
	 * @return
	 * @throws ClienteFacadeException
	 */
	public String salvarCliente(final String nome, final Integer idade, final String remoteAddr) throws ClienteFacadeException {
		try {
			GeoLocalizacao geoLocalizacao = getGeoLocalizacao(remoteAddr);
			GeoLocalizacao geoLocalizacaoPersitido = geoLocalizacaoBusiness.salvarGeoLocalizacao(geoLocalizacao);

			return clienteBusiness.salvarCliente(nome, idade, geoLocalizacaoPersitido);
		} catch (GeoLocalizacaoBusinessException | ClienteBusinessException e) {
			String msg = e.getMessage();
			logger.error(msg, e);
			throw new ClienteFacadeException(msg, e);
		}
	}

	/**
	 * Atualiza a entidade {@link Cliente} conforme os parâmetros fornecidos.
	 *
	 * @param nome
	 * @param idade
	 * @param id
	 * @return
	 * @throws ClienteFacadeException
	 */
	public String atualizarCliente(final String nome, final Integer idade, final Integer id) throws ClienteFacadeException {
		try {
			return clienteBusiness.atualizarCliente(nome, idade, id);
		} catch (ClienteBusinessException e) {
			String msg = e.getMessage();
			logger.error(msg, e);
			throw new ClienteFacadeException(msg, e);
		}
	}

	/**
	 * Exclui a entidade {@link Cliente}.
	 *
	 * @param id
	 * @return
	 * @throws ClienteFacadeException
	 */
	public String excluirCliente(final Integer id) throws ClienteFacadeException {
		if (id == null) {
			throw new ClienteFacadeException("O ID não foi informado ou é igual a zero. Impossível prosseguir.");
		}

		try {
			Cliente cliente = clienteBusiness.getClienteById(id);
			GeoLocalizacao geoLocalizacao = cliente.getGeoLocalizacao();

			clienteBusiness.excluirCliente(cliente);
			geoLocalizacaoBusiness.excluirGeoLocalizacao(geoLocalizacao);
		} catch (ClienteBusinessException | GeoLocalizacaoBusinessException e) {
			String msg = e.getMessage();
			logger.error(msg, e);
			throw new ClienteFacadeException(msg, e);
		}

		return "Cliente excluído com sucesso!";
	}

	/**
	 * Retorna a {@link GeoLocalizacao} conforme o ip remoto informado.
	 *
	 * @param remoteAddr
	 * @return
	 */
	private GeoLocalizacao getGeoLocalizacao(final String remoteAddr) {
		if (Util.isEmpty(remoteAddr)) {
			return null;
		}

		IpTO ipTO = ipVigilanteClient.getIpTO(remoteAddr);
		List<LocalizacaoTO> localizacoes = ipTO == null || ipTO.getData() == null ? new ArrayList<>()
				: metaWeatherClient.getLocalizacaoTO(ipTO.getData().getLatitude(), ipTO.getData().getLongitude());

		LocalizacaoTO localizacaoTO = localizacoes.stream().min(Comparator.comparing(LocalizacaoTO::getDistance)).orElse(null);
		WeatherTO weatherTO = localizacaoTO == null ? null : metaWeatherClient.getWeatherTO(localizacaoTO.getWoeid());

		IpDataTO dadosIp = ipTO == null ? null : ipTO.getData();

		ConsolidatedWeatherTO consolidatedWeatherTO = weatherTO == null ? null
				: weatherTO.getConsolidated_weather().stream().min(Comparator.comparing(ConsolidatedWeatherTO::getApplicable_date)).orElse(null);

		GeoLocalizacao geoLocalizacao = new GeoLocalizacao();

		if (dadosIp != null) {
			geoLocalizacao.setIpv4(dadosIp.getIpv4());
			geoLocalizacao.setContinente(dadosIp.getContinent_name());
			geoLocalizacao.setPais(dadosIp.getCountry_name());
			geoLocalizacao.setEstado(dadosIp.getSubdivision_1_name());
			geoLocalizacao.setLatitude(dadosIp.getLatitude());
			geoLocalizacao.setLongitude(dadosIp.getLongitude());
		}

		if (weatherTO != null) {
			geoLocalizacao.setIdRegiao(weatherTO.getWoeid());
			geoLocalizacao.setCidade(weatherTO.getTitle());
		}

		if (consolidatedWeatherTO != null) {
			geoLocalizacao.setDataTemperatura(consolidatedWeatherTO.getApplicable_date());
			geoLocalizacao.setTemperaturaMinima(consolidatedWeatherTO.getMin_temp());
			geoLocalizacao.setTemperaturaMaxima(consolidatedWeatherTO.getMax_temp());
		}

		return geoLocalizacao;
	}

	/**
	 * Retorna a lista de {@link ClienteTO} conforme a lista de {@link Cliente} fornecida.
	 *
	 * @param clientes
	 * @return
	 */
	private List<ClienteTO> getClientesTO(final List<Cliente> clientes) {
		List<ClienteTO> clientesTO = new ArrayList<>();
		clientes.forEach(cliente -> clientesTO.add(getClienteTO(cliente)));
		return clientesTO;
	}

	/**
	 * Retorna o {@link ClienteTO} conforme o {@link Cliente} fornecido.
	 *
	 * @param cliente
	 * @return
	 */
	private ClienteTO getClienteTO(final Cliente cliente) {
		GeoLocalizacao geoLocalizacao = cliente.getGeoLocalizacao();
		return new ClienteTO(
				(Integer) cliente.getId(), cliente.getNome(), cliente.getIdade(), geoLocalizacao.getIdRegiao(),
				geoLocalizacao.getLatitude(), geoLocalizacao.getLongitude(), geoLocalizacao.getCidade(),
				geoLocalizacao.getDataTemperatura(), geoLocalizacao.getTemperaturaMinima(), geoLocalizacao.getTemperaturaMaxima());
	}
}
