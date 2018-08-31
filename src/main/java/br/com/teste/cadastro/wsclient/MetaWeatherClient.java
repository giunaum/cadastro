package br.com.teste.cadastro.wsclient;

import br.com.teste.cadastro.to.LocalizacaoTO;
import br.com.teste.cadastro.to.WeatherTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

import java.util.Arrays;
import java.util.List;

/**
 * Classe Client para acessa os serviços da IP Vigilante.
 */
@Component
public class MetaWeatherClient {

	@Value("${metaweather.location.service}")
	private String URL_LOCATION;

	@Value("${metaweather.weather.service}")
	private String URL_WEATHER;

	@Autowired
	private RestOperations restOperations;

	/**
	 * Retorna o {@link LocalizacaoTO} do serviço.
	 *
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	public List<LocalizacaoTO> getLocalizacaoTO(final String latitude, final String longitude) {
		LocalizacaoTO[] localizacaosTO = restOperations.getForObject(URL_LOCATION, LocalizacaoTO[].class, latitude, longitude);
		return Arrays.asList(localizacaosTO);
	}

	/**
	 * Retorna o {@link LocalizacaoTO} do serviço.
	 *
	 * @param woeid
	 * @return
	 */
	public WeatherTO getWeatherTO(final Long woeid) {
		return restOperations.getForObject(URL_WEATHER, WeatherTO.class, woeid);
	}
}
