/**
 * 
 */
package br.com.lawoffice.caixa.extrato;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * Representa um item para a lista de extrato. 
 * 
 * @author rduarte
 *
 */
public class ItemExtrato implements Serializable, Comparable<ItemExtrato>{

	/**
	 *  serial version uid da classe.
	 */
	private static final long serialVersionUID = -2770861593925656092L;

	/**
	 * 
	 * Data do lancamento do item.
	 * 
	 */
	private Date dataLancamento;
	
	/**
	 *  Natureza do Item.
	 */
	private String natureza;
	
	/**
	 *  Valor do item.
	 */
	private BigDecimal valor;

	
	public ItemExtrato(Date dataLancamento, String natureza, BigDecimal valor) {
		super();
		this.dataLancamento = dataLancamento;
		this.natureza = natureza;
		this.valor = valor;
	}


	public Date getDataLancamento() {
		return dataLancamento;
	}


	public String getNatureza() {
		return natureza;
	}


	public BigDecimal getValor() {
		return valor;
	}


	@Override
	public int compareTo(ItemExtrato itemExtrato) {
		return this.dataLancamento.compareTo(itemExtrato.dataLancamento);
	}


	@Override
	public String toString() {
		return "ItemExtrato [dataLancamento=" + dataLancamento + ", natureza="
				+ natureza + ", valor=" + valor + "]";
	}
	
}
