/**
 * 
 */
package br.com.lawoffice.custas.lancamento;

import java.util.Date;
import java.util.Map;

import br.com.lawoffice.dominio.Cliente;
import br.com.lawoffice.dominio.Colaborador;

/**
 * 
 * Chave para o {@link Map} de lancamentos do serivco de lancamentos.
 * 
 * @author rduarte
 * 
 * @see LancamentoService
 *
 */
class ChaveLancamento {

	/**
	 * Cliente do lancamento.
	 */
	private Cliente cliente;
	
	/**
	 * Colaborador do lancamento.
	 */
	private Colaborador colaborador;
	
	/**
	 * Data do lancamento.
	 */
	private Date data;

	public ChaveLancamento(Cliente cliente, Colaborador colaborador, Date data) {		
		if( cliente == null 
				|| colaborador == null 
				|| data == null);			
		this.cliente = cliente;
		this.colaborador = colaborador;
		this.data = data;
	}
		

	public Cliente getCliente() {
		return cliente;
	}



	public Colaborador getColaborador() {
		return colaborador;
	}



	public Date getData() {
		return data;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result
				+ ((colaborador == null) ? 0 : colaborador.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		return result;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChaveLancamento other = (ChaveLancamento) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (colaborador == null) {
			if (other.colaborador != null)
				return false;
		} else if (!colaborador.equals(other.colaborador))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "ChaveLancamento [cliente=" + cliente + ", colaborador="
				+ colaborador + ", date=" + data + "]";
	}
	
}
