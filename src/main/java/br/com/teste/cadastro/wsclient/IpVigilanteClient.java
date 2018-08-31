package br.com.teste.cadastro.wsclient;

import br.com.teste.cadastro.to.IpTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

/**
 * Classe Client para acessa os serviços da IP Vigilante.
 */
@Component
public class IpVigilanteClient {

	@Value("${ipvigilante.service}")
	private String URL;

	@Autowired
	private RestOperations restOperations;

	/**
	 * Retorna o {@link IpTO} do serviço.
	 *
	 * @param ip
	 * @return
	 */
	public IpTO getIpTO(final String ip) {
		return restOperations.getForObject(URL, IpTO.class, ip);
	}
}
