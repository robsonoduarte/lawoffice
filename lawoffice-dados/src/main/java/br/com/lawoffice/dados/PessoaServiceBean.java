/**
 *
 */
package br.com.lawoffice.dados;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.lawoffice.dominio.Cliente;
import br.com.lawoffice.dominio.Custa;
import br.com.lawoffice.dominio.Lancamento;
import br.com.lawoffice.dominio.Pessoa;
import br.com.lawoffice.persistencia.CustaDao;
import br.com.lawoffice.persistencia.LancamentoDao;
import br.com.lawoffice.persistencia.PessoaDao;

/**
 *
 * Classe de implemtacao para  {@link PessoaDao} utilizando EJB 3.1
 *
 * @author robson
 *
 */
@Stateless
@Local(PessoaServiceLocal.class)
@Remote(PessoaServiceRemote.class)
public class PessoaServiceBean  implements PessoaService {


	/**
	 * Dao para operacoes de base para {@link Pessoa}.
	 */
	@EJB
	private PessoaDao pessoaDao;


	@EJB
	private LancamentoDao lancamentoDao;


	@EJB
	private CustaDao custaDao;


	@Override
	public <T extends Pessoa> List<T> listarPorNome(Class<T> c, String nome) {
		if(c == null || nome == null)
			throw new IllegalArgumentException("c esta nullo ou nome esta nulo");
		return pessoaDao.listarPorNome(c, nome);
	}



	@Override
	public <T extends Pessoa> T atualizar(T t) {
		if(t== null )
			throw new IllegalArgumentException("t esta null");
		return pessoaDao.atualizar(t);
	}



	@Override
	public <T extends Pessoa> void remover(Class<T> c, T t) {
		if(c == null || t == null )
			throw new IllegalArgumentException("c esta nulo ou t esta nullo");

		List<Lancamento> lancamentos = null;

		if(t instanceof Cliente ){
			lancamentos = lancamentoDao.getLancamentos((Cliente) t);
		}

		for (Lancamento lancamento : lancamentos) {
			List<Custa> custas = lancamento.getCustas();

			for (Custa custa : custas) {
				custaDao.remover(Custa.class, custa);
			}

			lancamentoDao.remover(Lancamento.class, lancamento);
		}

		pessoaDao.remover(c, t);
	}



	@Override
	public <T extends Pessoa> T localizar(Class<T> c, T t) {
		if(c == null || t == null )
			throw new IllegalArgumentException("c esta nulo ou t esta nullo");
		return pessoaDao.localizar(c, t);
	}


	@Override
	public <T extends Pessoa> T salvar(T t) {
		if(t == null )
			throw new IllegalArgumentException("t esta nullo");
		return pessoaDao.salvar(t);
	}


	@Override
	public <T extends Pessoa> List<T> listar(Class<T> c) {
		if(c == null)
			throw new IllegalArgumentException("c esta nullo");
		return pessoaDao.listar(c);
	}

}
