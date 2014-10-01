package br.com.lawoffice.caixa.extrato;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.lawoffice.dominio.Conta;
import br.com.lawoffice.dominio.Pessoa;

/**
 * 
 * DTO para exibição e criação do Extrato de consulta. 
 * 
 * 
 * @author robson
 *
 */
public class ExtratoDTO implements Serializable{

	
	/**
	 *  serial version uid da classe.
	 */
	private static final long serialVersionUID = 7488732670828611506L;

	/**
	 * Nome da pessoa que o extrato será gerado.
	 */
	private String nomePessoa;
	
	/**
	 * Saldo anterior da data inicial da consulta; 
	 */
	private BigDecimal saldoAnterior;
	
	/**
	 * Saldo atual da {@link Conta} da {@link Pessoa}.
	 */
	private BigDecimal saldoAtual;
	
	/**
	 * Data inicial do periodo da consulta.
	 */
	private Date dataInicial;
	
	/**
	 * Data final do periodo da consulta;
	 */
	private Date dataFinal;
	
	/**
	 *  Itens do extrado. 
	 */
	private List<ItemExtrato> itensExtrato;


	/**
	 * Construtor padrao para criacao do {@link ExtratoDTO}.
	 * 
	 * <br>
	 * 
	 * OBS. todos sao obrigatorio caso um paramentro esteja nulo uma {@link IllegalArgumentException} será lançada.
	 * 
	 * @param nomePessoa - nome da pessoa pesquisada
	 * @param saldoAnterior - saldo anterior a data inicial da pesquisa.
	 * @param saldoAtual - saldo atual da conta.
	 * @param dataInicial - data inicial da pesquisa.
	 * @param dataFinal - data final da pesquisa.
	 * @throws IllegalArgumentException - quando algum parametro estiver nulo
	 */
	public ExtratoDTO(String nomePessoa, BigDecimal saldoAnterior,
			BigDecimal saldoAtual, Date dataInicial, Date dataFinal) {
		if(nomePessoa == null 
				|| saldoAnterior == null 
				|| saldoAtual == null 
				|| dataFinal == null 
				|| dataFinal == null)
			throw new IllegalArgumentException("existe paramentro(s) nulo(s)");
		
		this.nomePessoa = nomePessoa;
		this.saldoAnterior = saldoAnterior;
		this.saldoAtual = saldoAtual;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		itensExtrato = new ArrayList<ItemExtrato>();
	}

	
	
	public void addItemExtrato(ItemExtrato itemExtrato){
		if(itemExtrato !=null){
			itensExtrato.add(itemExtrato);
		}
	}

	
	public String getNomePessoa() {
		return nomePessoa;
	}

	public BigDecimal getSaldoAnterior() {
		return saldoAnterior;
	}

	public BigDecimal getSaldoAtual() {
		return saldoAtual;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public List<ItemExtrato> getItensExtrato() {
		return itensExtrato;
	}



	@Override
	public String toString() {
		return "ExtratoDTO [nomePessoa=" + nomePessoa + ", saldoAnterior="
				+ saldoAnterior + ", saldoAtual=" + saldoAtual
				+ ", dataInicial=" + dataInicial + ", dataFinal=" + dataFinal
				+ ", itensExtrato=" + itensExtrato + "]";
	}
	
}
