package br.com.teste.cadastro.entities;

import javax.persistence.*;

/**
 * Classe que representa a entidade Cliente na tabela.
 */
@Entity
public class Cliente implements Entidade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(length = 100, nullable = false)
	private String nome;

	@Column(nullable = false)
	private Integer idade;

	@OneToOne
	private GeoLocalizacao geoLocalizacao;

	@Override
	public Object getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public GeoLocalizacao getGeoLocalizacao() {
		return geoLocalizacao;
	}

	public void setGeoLocalizacao(GeoLocalizacao geoLocalizacao) {
		this.geoLocalizacao = geoLocalizacao;
	}
}