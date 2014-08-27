package br.com.lawoffice.caixa;

import java.math.BigDecimal;
import java.util.Date;

import br.com.lawoffice.dominio.Conta;

/**
 * Interface de Serviço para realizar operações no caixa do escritório. 
 * 
 * @author robson
 * 
 * @see {@link CaixaServiceBean} {@link CaixaServiceLocal} {@link CaixaServiceRemote}
 *
 */
public interface CaixaService {
	
	/**
	 * Realiza o crédito na {@link Conta} passada.
	 * 
	 * @param conta para realizar o crédito.
	 * @param valor do crédito
	 * @param data realizada o credito.
	 * @return {@link Conta} com o valor créditado.
	 * @throws IllegalArgumentException  quando {@link Conta} estiver nula ou nao possuir id ou nao ser encontrada , valor do credito nulo ou menor que zero, data nula
	 */
	public Conta creditar(Conta conta, BigDecimal valor, Date data);
	
	/**
	 * Realiza o débito na {@link Conta} passada.
	 * 
	 * @param conta para realizar o débito.
	 * @param valor do débito.
	 * @param data realizada o debito.
	 * @return {@link Conta} com o valor débitado.
	 * @throws IllegalArgumentException  quando a {@link Conta} estiver nula ou nao possuir id ou nao ser encontrada , valor do credito nulo ou menor que zero, data nula.
	 */
	public Conta debitar(Conta conta, BigDecimal valor, Date data);
}
