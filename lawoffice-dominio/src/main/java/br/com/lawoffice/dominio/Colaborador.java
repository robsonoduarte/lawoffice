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
 * Representa uma pessoa que trabalha no escritório.
 * 
 * @author robson
 *
 */
@Entity
@Table(name="COLABORADOR")
public class Colaborador extends Pessoa{

	
	/**
	 * Cargo do colaborador no escritorio. 
	 */
	@Column(name="CARGO")
	private String cargo;
	
	
	/**
	 * lancamentos do colaborador. 
	 */
	@OneToMany(mappedBy="colaborador")
	private List<Lancamento> lancamentos;
	
	
	/**
	 * Conta do colaborador no escritorio.
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="CONTA_ID")
	private Conta conta;

	
	/**
	 * agenda de compromissos do colaborador. 
	 */
	@OneToOne(mappedBy="colaborador", cascade = CascadeType.ALL)
	private Agenda agenda;
	

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

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

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	@Override
	public String toString() {
		return "Colaborador [cargo=" + cargo + ", lancamentos=" + lancamentos
				+ ", conta=" + conta + ", agenda=" + agenda + "]";
	}	
	
}
