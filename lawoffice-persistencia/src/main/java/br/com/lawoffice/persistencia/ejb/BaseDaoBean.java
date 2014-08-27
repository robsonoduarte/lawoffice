package br.com.lawoffice.persistencia.ejb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import br.com.lawoffice.dominio.EntityBase;
import br.com.lawoffice.persistencia.BaseDao;

/**
 * Implementação Base para o {@link BaseDao} utilizando a tecnoliga EJB 3.1.
 * 
 * @author robson
 *
 */
public class BaseDaoBean implements BaseDao {

	
	@PersistenceContext(name="lawoffice-persistencia")
	protected EntityManager entityManager;
	

	@Override
	public <T extends EntityBase> T atualizar(T t) {		
		return entityManager.merge(t);
	}


	@Override
	public <T extends EntityBase> void remover(Class<T> c, T t) {
		entityManager.remove(localizar(c, t));

	}

	@Override
	public <T extends EntityBase> T localizar(Class<T> c, T t) {
		return entityManager.find(c, t.getId());
	}

	
	@Override
	public <T extends EntityBase> T salvar(T t) { 
		return entityManager.merge(t);
	}

	@Override
	public <T extends EntityBase> List<T> listar(Class<T> c) {
		
		CriteriaQuery<T> criteriaQuery =
			entityManager
			.getCriteriaBuilder()
			.createQuery(c);
		
		criteriaQuery.from(c);
		
		return entityManager
			.createQuery(criteriaQuery)
			.getResultList();
	}

}
