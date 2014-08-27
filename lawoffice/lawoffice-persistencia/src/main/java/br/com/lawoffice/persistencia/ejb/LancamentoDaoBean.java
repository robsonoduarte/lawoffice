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

import br.com.lawoffice.dominio.Cliente;
import br.com.lawoffice.dominio.Colaborador;
import br.com.lawoffice.dominio.Lancamento;
import br.com.lawoffice.persistencia.ColaboradorDao;
import br.com.lawoffice.persistencia.LancamentoDao;

/**
 * classe de implementacao para o {@link ColaboradorDao} utilizando tecnologia EJB 3.1
 *
 * @author robson
 *
 */
@Stateless
@Local(LancamentoDao.class)
public class LancamentoDaoBean extends BaseDaoBean implements LancamentoDao {

	@Override
	public List<Lancamento> getLancamentos(Date dataInicial, Date dataFinal,
			Colaborador colaborador) {

		CriteriaBuilder criteriaBuilder =
				entityManager.getCriteriaBuilder();


		CriteriaQuery<Lancamento> criteriaQuery =
				criteriaBuilder.createQuery(Lancamento.class);


		Root<Lancamento> root =
				criteriaQuery.from(Lancamento.class);


		criteriaQuery.select(root)
			.where(
					criteriaBuilder.and(
						criteriaBuilder.between(root.get("dataLancamento").as(Date.class), dataInicial, dataFinal),
						criteriaBuilder.equal(root.get("colaborador"), colaborador)
					)
				).orderBy(
						criteriaBuilder.asc(root.get("dataLancamento").as(Date.class))
					);

		return entityManager
				.createQuery(criteriaQuery)
				.getResultList();
	}



	@Override
	public List<Lancamento> getLancamentos(Date dataInicial, Date dataFinal,
			Cliente cliente) {

		CriteriaBuilder criteriaBuilder =
				entityManager.getCriteriaBuilder();


		CriteriaQuery<Lancamento> criteriaQuery =
				criteriaBuilder.createQuery(Lancamento.class);


		Root<Lancamento> root =
				criteriaQuery.from(Lancamento.class);


		criteriaQuery.select(root)
			.where(
					criteriaBuilder.and(
						criteriaBuilder.between(root.get("dataLancamento").as(Date.class), dataInicial, dataFinal),
						criteriaBuilder.equal(root.get("cliente"), cliente)
					)
				).orderBy(
						criteriaBuilder.asc(root.get("dataLancamento").as(Date.class))
					);

		return entityManager
				.createQuery(criteriaQuery)
				.getResultList();
	}



	@Override
	public List<Lancamento> getLancamentos(Cliente cliente) {

		CriteriaBuilder criteriaBuilder =
				entityManager.getCriteriaBuilder();

		CriteriaQuery<Lancamento> criteriaQuery =
				criteriaBuilder.createQuery(Lancamento.class);

		Root<Lancamento> root =
				criteriaQuery.from(Lancamento.class);

		criteriaQuery.select(root)
			.where(criteriaBuilder.equal(root.get("cliente"), cliente));

		return entityManager
				.createQuery(criteriaQuery)
				.getResultList();
	}

}
