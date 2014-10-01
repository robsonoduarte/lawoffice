package br.com.lawoffice.persistencia;

import java.util.List;

import br.com.lawoffice.dominio.EntityBase;

/**
 * DAO com as operações básicas para trabalhar com as entidades de dominio.
 * 
 * @author robson
 */
public interface BaseDao {
	
	
	/**
	 * Atualiza a entidade passada na base de dados.
	 * 
	 * @param <T> - Entidade do Tipo {@link EntityBase}
	 * @param t  - Entidade a ser atualizada.
	 * @return T - Entidade atualizada.
	 * @throws IllegalArgumentException - quando t não for uma classe persistente (entidade).
	 */	
	public <T extends EntityBase> T atualizar(T t);

	
	/**
	 * Remove a entidade passada na base de dados.
	 * 
	 * @param <T> - Entidade do Tipo {@link EntityBase} 
	 * @param t - Entidade a ser removida.
	 * @throws IllegalArgumentException - quando t não for uma classe persistente (entidade).
	 */
	public <T extends EntityBase> void remover(Class<T> c, T t);
	

	/**
	 * Localiza a Entidade passada na base de dados através da sua chave primaria.
	 * 
	 * @param <T> - Entidade do Tipo {@link EntityBase}.
	 * @param c - {@link Class} do tipo {@link EntityBase}.
	 * @param t - Entidade a ser localizada.
	 * @return T - Entidade localizada on null se a entidade não exisitir.
	 * @throws IllegalArgumentException  quando t não for uma classe persistente.
	 * 
	 * */
	public <T extends EntityBase> T localizar(Class<T> c, T t);
	
	/**
	 * Persiste a Entidade passada na base de dados.
	 * 
	 * @param <T> - Entidade do Tipo {@link EntityBase}
	 * @param t - Entidade a ser persistida.
	 * @return T - Entidade persistida.
	 * @throws IllegalArgumentException - quando t não for uma classe persistente (entidade).
	 */
	public <T extends EntityBase> T salvar(T t);
	
	
	/**
	 * Lista todas as Entidade do tipo passado na base de dados.
	 * 
	 * @param <T> - Entidade do Tipo {@link EntityBase}. 
	 * @param tClass - {@link Class} da Entidade a ser listada.
	 * @return {@link List} - de Entidade  encontrada na base de dados.
	 */
	public <T extends EntityBase> List<T> listar(Class<T> tClass);
		
}
