/**
 * 
 */
package br.com.lawoffice.persistencia.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.lawoffice.dominio.Pessoa;
import br.com.lawoffice.persistencia.PessoaDao;

/**
 * classe de implementacao para o {@link PessoaDao} utilizando tecnologia EJB 3.1
 * 
 * @author robson
 *
 */
@Local(PessoaDao.class)
@Stateless
public class PessoaDaoBean extends BaseDaoBean implements PessoaDao {


	@Override
	public <T extends Pessoa> List<T> listarPorNome(Class<T> c, String nome) {
		
		CriteriaBuilder criteriaBuilder =
			entityManager.getCriteriaBuilder();		
		
		CriteriaQuery<T> criteriaQuery  =
			criteriaBuilder.createQuery(c);
		
		Root<T> root =
			criteriaQuery.from(c);		

		criteriaQuery
			.select(root)
			.where( 
				criteriaBuilder.like(
					root.get("nome").as(String.class), 
					nome + "%"  
				)
			);
				
		return entityManager
			.createQuery(criteriaQuery)
			.getResultList();
	}

}
