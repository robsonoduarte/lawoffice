package br.com.lawoffice.dominio;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 
 * Classe base para entidades que  representa pessoas no escritorio.
 * 
 * @author robson
 * 
 * @see  {@link Colaborador} {@link Cliente}
 *
 */
@MappedSuperclass
public class Pessoa implements EntityBase{

	/**
	 * identificador da entidade. 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")	
	protected Long id;
	
	/**
	 * nome da pessoa.
	 */
	@Column(name = "NOME")
	protected String nome;
	
	/**
	 * email da pessoa. 
	 */
	@Column(name = "EMAIL")
	protected String email;
	

	public Pessoa() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}