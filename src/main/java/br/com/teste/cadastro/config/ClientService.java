package br.com.teste.cadastro.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

/**
 * Classe de configuração do Client Service.
 */
@Configuration
public class ClientService {

	@Value("${read.timeout}")
	private int READ_TIMEOUT;

	@Value("${connect.timeout}")
	private int CONNECT_TIMEOUT;

	@Bean
	public RestOperations createRestTemplate(final ClientHttpRequestFactory clientHttpRequestFactory) {
		return new RestTemplate(clientHttpRequestFactory);
	}

	@Bean
	public ClientHttpRequestFactory createClientHttpRequestFactory() {
		HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		httpComponentsClientHttpRequestFactory.setReadTimeout(READ_TIMEOUT);
		httpComponentsClientHttpRequestFactory.setConnectTimeout(CONNECT_TIMEOUT);
		return httpComponentsClientHttpRequestFactory;
	}
}
