/**
 * 
 */
package br.com.lawoffice.dominio;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * Representa um cliente do escritório.
 * 
 * @author robson
 *
 */
@Entity
@Table(name="CLIENTE")
public class Cliente extends Pessoa{

	
	
	/**
	 * servial version uid da entidade.
	 */
	private static final long serialVersionUID = -5411526954284584650L;
	

	/**
	 * Lista de lancamentos do cliente. 
	 */
	@OneToMany(mappedBy = "cliente")
	private List<Lancamento> lancamentos;
	
	/**
	 * telefone do cliente.
	 */
	@Column(name="TELEFONE")
	private String telefone;	
	
	
	/**
	 * conta do cliente no escritorio.
	 */
	@OneToOne(cascade = CascadeType.ALL)	
	@JoinColumn(name="CONTA_ID")
	private Conta conta;
	
	
	

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public int hashCode() {
		return 31 * ((id == null) ? 0 : id.intValue());
	}

	
	
	@Override
	public boolean equals(Object cliente) {
		if (this == cliente)
			return true;
		if (cliente == null)
			return false;
		if (getClass() != cliente.getClass())
			return false;
		return this.id.equals(((Cliente) cliente).id);
	}

	
	@Override
	public String toString() {
		return "Cliente [" +
				"lancamentos=" + lancamentos + 
				", conta=" + conta+ 
				", telefone=" + telefone + 
			"]";
	}

}
