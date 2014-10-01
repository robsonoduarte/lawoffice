/**
 * 
 */
package br.com.lawoffice.persistencia.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.lawoffice.dominio.Colaborador;
import br.com.lawoffice.dominio.Evento;
import br.com.lawoffice.persistencia.EventoDao;

/**
 * Implementação do {@link EventoDao} utilizando tecnologia EJB 3.1
 * 
 * @author robson
 *
 */
@Stateless
@Local(EventoDao.class)
public class EventoDaoBean extends BaseDaoBean implements EventoDao {

	
	@Override
	public List<Evento> getEventos(Colaborador colaborador, Date dataInicial,
			Date dataFinal) {
		
		CriteriaBuilder criteriaBuilder =
			entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Evento> criteriaQuery =
			criteriaBuilder.createQuery(Evento.class);
		
		Root<Evento> root =
			criteriaQuery.from(Evento.class);
		
		criteriaQuery.select(root)
			.where(
				criteriaBuilder.and(
						criteriaBuilder.equal(root.get("agenda").get("colaborador"), colaborador ),
						criteriaBuilder.between(root.get("dataIncial").as(Date.class), dataInicial, dataFinal)
					)
			);
		
		return entityManager
			.createQuery(criteriaQuery)
			.getResultList();
	}

}
