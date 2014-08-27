package br.com.lawoffice.caixa;

import java.math.BigDecimal;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.lawoffice.dominio.Conta;
import br.com.lawoffice.dominio.HistoricoConta;
import br.com.lawoffice.dominio.TipoTransacao;
import br.com.lawoffice.persistencia.ContaDao;

/**
 * Implementação para a Interface de serviços de caixa do escritório.
 * 
 * @author robson
 *
 */
@Stateless
@Local(CaixaServiceLocal.class)
@Remote(CaixaServiceRemote.class)
public class CaixaServiceBean implements CaixaService {
	

	/**
	 * Dao para operacoes de persistencia para {@link Conta}.
	 */
	@EJB
	private ContaDao contaDao;

	
	@Override
	public Conta creditar(Conta conta, BigDecimal valor, Date date){
		validarParamentros(conta, valor,date);
	
		conta = getConta(conta); 

		
		contaDao.salvar(
			new HistoricoConta(
				date, 
				TipoTransacao.CREDITO, 
				valor, 
				conta.getSaldo(), 
				conta
			)
		);
		 
		
		conta.setSaldo(
				conta.getSaldo().add(valor)
			);

		return contaDao.atualizar(conta);
	}

	
	
	@Override
	public Conta debitar(Conta conta, BigDecimal  valor, Date date){
		validarParamentros(conta, valor, date);
		
		conta = getConta(conta);

		contaDao.salvar(
			new HistoricoConta(
				date, 
				TipoTransacao.DEBITO, 
				valor, 
				conta.getSaldo(), 
				conta
			)
		);
		 
		conta.setSaldo(
				conta.getSaldo().subtract(valor)
			);
		
		return contaDao.atualizar(conta);
	}

	
	

	/**
	 * Retorna a Conta pelo contexto do EntityManager
	 * 
	 * @param conta passda para adicionar ao contexto do EntiyManager.
	 * @return {@link Conta} no contexto do EntityManager.
	 * @throws CaixaException caso a {@link Conta} não seja encontrada na base de dados.
	 */
	private Conta getConta(Conta conta){		
		conta = contaDao.localizar(Conta.class, conta);				
		if(conta == null)
			throw new IllegalArgumentException("Conta não encontrado");		
		return conta;
	}	

	

	/**
	 * 
	 * Realiza as validações dos parametros passado para realizar as operações de caixa.
	 * 
	 * @param conta a ser validada.
	 * @param valor a ser validado.
	 * @param date a ser validada.
	 */
	private void validarParamentros(Conta conta, BigDecimal valor, Date date) {
		if(valor == null || valor.doubleValue() < 0)
			throw new IllegalArgumentException("O valor está nulo ou é menor que 0");
		if(conta == null || conta.getId() == null)
			throw new IllegalArgumentException("Conta está nula ou nao contém ID");
		if(date == null)
			throw new IllegalArgumentException("Data está nula");		
	}


	
	public void setContaDao(ContaDao contaDao) {
		this.contaDao = contaDao;
	}
	
}
