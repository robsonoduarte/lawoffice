/**
 *
 */
package br.com.lawoffice.persistencia;

import java.util.Date;
import java.util.List;

import br.com.lawoffice.dominio.Cliente;
import br.com.lawoffice.dominio.Colaborador;
import br.com.lawoffice.dominio.Lancamento;
import br.com.lawoffice.dominio.Pessoa;

/**
 *
 * Interface para operacoes de persistencia para o {@link Lancamento}.
 *
 * @author robson
 *
 */
public interface LancamentoDao extends BaseDao {

	/**
	 * Pesquisa e retorna os lançamentos de custas do colaborodar no periodo passado
	 * ordenandas de forma crescente pela data de lançamento.
	 *
	 *
	 * @param dataInicial - para consulta.
	 * @param dataFinal - para consulta.
	 * @param colaborador - para consulta.
	 * @return {@link List} de {@link Lancamento} como resultado da pesquisa.
	 */
	List<Lancamento> getLancamentos(Date dataInicial, Date dataFinal, Colaborador colaborador);


	/**
	 * Pesquisa e retorna os lançamentos de custas do cliente no periodo passado
	 * ordenandas de forma crescente pela data de lançamento.
	 *
	 * @param dataInicial - para consulta.
	 * @param dataFinal - para consulta.
	 * @param cliente - para consulta.
	 * @return {@link List} de {@link Lancamento} como resultado da pesquisa.
	 */
	List<Lancamento> getLancamentos(Date dataInicial, Date dataFinal, Cliente cliente);

	/**
	 * Pesquisa e retorna todos os lançamentos de custas do cliente
	 *
	 * @param cliente - para consulta.
	 * @return {@link List} de {@link Lancamento} como resultado da pesquisa.
	 */
	 List<Lancamento> getLancamentos(Cliente cliente);
}
