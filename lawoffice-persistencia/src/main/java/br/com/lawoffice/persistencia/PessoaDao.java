/**
 * 
 */
package br.com.lawoffice.persistencia;

import java.util.List;

import br.com.lawoffice.dominio.Cliente;
import br.com.lawoffice.dominio.Colaborador;
import br.com.lawoffice.dominio.Pessoa;

/**
 * 
 * Dao Base para as pessoas envolvidas no escritório >> {@link Colaborador} {@link Cliente}
 * 
 * @author robson
 *
 */
public interface PessoaDao extends BaseDao {

	
	/**
	 * Pesquisa e retorna um {@link List} de {@link Pessoa} tendo como criterio se o nome passado
	 * 
	 * esta contindo no nome da {@link Pessoa}.
	 * 
	 * @param c - {@link Class} do tipo {@link Pessoa}.
	 * @param <T> - {@link Pessoa}.
	 * @param nome - para base da pesquisa.
	 * @return {@link List} de {@link Pessoa} que possuir o nome passado.
	 */
	public <T extends Pessoa> List<T> listarPorNome(Class<T> c , String nome);
}
