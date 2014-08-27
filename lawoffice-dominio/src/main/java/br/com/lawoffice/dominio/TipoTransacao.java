/**
 * 
 */
package br.com.lawoffice.dominio;

/**
 * 
 * Tipos de transações realizada em uma {@link Conta}
 * 
 * @author robson
 *
 */
public enum TipoTransacao {

	DEBITO("Débito"),
	CREDITO("Crédito");
	
	private String value;
	
	private TipoTransacao(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
