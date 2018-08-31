package br.com.teste.cadastro.to;

import java.io.Serializable;

/**
 * Classe que representa as fontes do clima.
 */
public class SourceTO implements Serializable {

	private String title;
	private String slug;
	private String url;
	private String crawl_rate;

	/**
	 * Construtor da classe sem parâmetros.
	 */
	public SourceTO() {}

	/**
	 * Construtor da classe com parâmetros.
	 *
	 * @param title
	 * @param slug
	 * @param url
	 * @param crawl_rate
	 */
	public SourceTO(String title, String slug, String url, String crawl_rate) {
		this.title = title;
		this.slug = slug;
		this.url = url;
		this.crawl_rate = crawl_rate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCrawl_rate() {
		return crawl_rate;
	}

	public void setCrawl_rate(String crawl_rate) {
		this.crawl_rate = crawl_rate;
	}
}
