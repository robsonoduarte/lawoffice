/**
 * 
 */
package br.com.lawoffice.persistencia;

import java.util.Date;
import java.util.List;

import br.com.lawoffice.dominio.Cliente;
import br.com.lawoffice.dominio.Colaborador;
import br.com.lawoffice.dominio.Conta;
import br.com.lawoffice.dominio.HistoricoConta;


/**
 * Dao para realizar operacoes de persistencia para a {@link HistoricoConta} do {@link Colaborador} ou {@link Cliente} do escritorio.
 * 
 * @author robson
 *
 */
public interface HistoricoContaDao extends BaseDao {

	/**
	 * Pesquisa e retorna um {@link List} de {@link HistoricoConta} da {@link Conta} passada utilizando como criterio de filtro a data passada e
	 * orderna de forma crescente pela data da transacao do historico. 
	 *  
	 * <br>
	 * 	  
	 * Caso nao haja {@link HistoricoConta} para data passada um {@link List} vazio será retornado.
	 * 
	 * @param date - para filtro da pesquisa.
	 * @param conta - a ser pesquisada os seus historicos.
	 * @return {@link List} - com os {@link HistoricoConta} na data passada ou vazio caso nao haja historico para data passada.
	 */
	List<HistoricoConta> getHistoricosConta(Date date, Conta conta);
	
	
	
	/**
	 * Pesquisa e retorna um {@link List} de {@link HistoricoConta} da {@link Conta} passada utilizando com criterio de filtro o perido das datas passadas
	 * e ordena de forma crescente pela data data transacao do historico.
	 * 
	 * <br>
	 * 	  
	 * Caso nao haja {@link HistoricoConta} para o periodo passado um {@link List} vazio será retornado.	 * 
	 * 
	 * @param dataInicial - para consulta.
	 * @param dataFinal - para consulta.
	 * @param conta - da consulta.
	 * @return {@link List} - com os {@link HistoricoConta} no periodo passado, ou vazio caso nao haja historico para o periodo passado.
	 */
	List<HistoricoConta> getHistoricosConta(Date dataInicial, Date dataFinal, Conta conta);
}
