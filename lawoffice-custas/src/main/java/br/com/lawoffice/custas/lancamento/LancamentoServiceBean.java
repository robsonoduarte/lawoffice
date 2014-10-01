/**
 * 
 */
package br.com.lawoffice.custas.lancamento;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateful;

import br.com.lawoffice.caixa.CaixaServiceLocal;
import br.com.lawoffice.dominio.Cliente;
import br.com.lawoffice.dominio.Colaborador;
import br.com.lawoffice.dominio.Custa;
import br.com.lawoffice.dominio.Lancamento;
import br.com.lawoffice.persistencia.LancamentoDao;
import br.com.lawoffice.persistencia.PessoaDao;

/**
 *  implementacao para o serviço de lancamento utilizando tecnologia EJB.
 * 
 * @author robson
 *
 */
@Stateful
@Local(LancamentoServiceLocal.class)
@Remote(LancamentoServiceRemote.class)
public class LancamentoServiceBean implements LancamentoService {
	
	
	/**
	 * Caixa para fechamento do lançamento , debito para o cliente , credito para o colaborador
	 */
	@EJB
	private CaixaServiceLocal caixaService;
	

	@EJB
	private PessoaDao pessoaDao;
	
	
	@EJB
	private LancamentoDao lancamentoDao;
	
	
	/**
	 *	Mapa com os laçamentos  
	 */
	private Map<ChaveLancamento, Lancamento> mapsLacamentos = new HashMap<ChaveLancamento, Lancamento>();
	
	

	@Override
	public Custa adicionarCusta(Custa custa, Cliente cliente, Colaborador colaborador, Date data){
		validarParametros(custa,cliente,colaborador,data);
		
		cliente = 
				pessoaDao.localizar(Cliente.class, cliente);
		
		if(cliente == null)
			throw new IllegalArgumentException("Cliente na encontrado nao base de dados");
		
		
		colaborador =
				pessoaDao.localizar(Colaborador.class, colaborador);
		
		
		if(colaborador == null)
			throw new IllegalArgumentException("Colaborador nao encontrado na base de dados");
		
		
		return custa.addLancamento(
				getLancamento(
					new ChaveLancamento(
							cliente, 
							colaborador, 
							data
						)
					).addCusta(custa)
			);
	}
	


	@Override
	public void fecharLacamento(){
		
		if(mapsLacamentos.isEmpty())
			throw new IllegalStateException("Não há lançamento(s) para fechar");

		
		List<Lancamento> lancamentos =  
				new ArrayList<Lancamento>(mapsLacamentos.values());
		
		
		for (Lancamento lancamento : lancamentos){
			
			caixaService.creditar(
				lancamento.getColaborador().getConta(), 
				lancamento.getTotal(),
				lancamento.getDataLancamento()
			);
			
			caixaService.debitar(
				lancamento.getCliente().getConta(),
				lancamento.getTotal(),
				lancamento.getDataLancamento()
			);
			
			lancamentoDao.salvar(lancamento);					
		}
		
		mapsLacamentos.clear();
	}
	
	
	@Override
	public void removerCusta(Custa custa){
		validarCusta(custa);
		
		Lancamento lancamento =  
			mapsLacamentos.get(
				new ChaveLancamento(
					custa.getLancamento().getCliente(), 
					custa.getLancamento().getColaborador(), 
					custa.getLancamento().getDataLancamento()
				)
			);
		
		if(lancamento  == null)
			throw new IllegalStateException("O Lançamento da custa não está na sessão do Bean");		
		
		lancamento.getCustas().remove(custa);
	}
	
	
	/**
	 * Retorna o Lançamento para a chave passada, caso nao haja lancamento para a chave um novo sera criado.
	 * 
	 * 
	 * @param cliente
	 * @param colaborador
	 * @return
	 */
	private Lancamento getLancamento(ChaveLancamento  chaveLancamento){	
	
		if( !mapsLacamentos.containsKey(chaveLancamento))
			mapsLacamentos.put(
					chaveLancamento, 
					new Lancamento()
						.adicionarCliente(chaveLancamento.getCliente())
						.adicionarColaborador(chaveLancamento.getColaborador())
						.adicionarDataLancamento(chaveLancamento.getData())
				);
		
		return mapsLacamentos.get(chaveLancamento);
	}
	

	

	
	
	
	/**
	 * valida os paramentros de entrada do servico.
	 * 
	 * @param custa - a ser validada.
	 * @param cliente - a ser validado.
	 * @param colaborador - a ser validado.
	 * @param data - a ser validada.
	 * @throws IllegalArgumentException quando algum parametro noa estiver  correto.
	 */
	private void validarParametros(Custa custa, Cliente cliente, Colaborador colaborador, Date data) {
		if(custa == null)
			throw new IllegalArgumentException("Custa está nula");
		if(cliente == null || cliente.getId() == null)
			throw new IllegalArgumentException("Cliente está nulo ou id cliente nulo");
		if(colaborador == null || colaborador.getId() == null)
			throw new IllegalArgumentException("Colaborador está nulo ou id colaborador nulo");
		if(data == null)
			throw new IllegalArgumentException("Data está nula");
	}
	

	
	

	/**
	 * valida uma custa
	 * 
	 * @param custa - a ser validada.
	 * @throws IllegalArgumentException quando a custa estiver ou nao estar associada a um lancamento.
	 */
	private void validarCusta(Custa custa) {
		if(custa == null)
			throw new IllegalArgumentException("Custa está nula");
		if(custa.getLancamento() == null)
			throw new IllegalArgumentException("Custa não está associada a um lancamento");
	}	


	public void setCaixaService(CaixaServiceLocal caixaService) {
		this.caixaService = caixaService;
	}



	public void setPessoaDao(PessoaDao pessoaDao) {
		this.pessoaDao = pessoaDao;
	}



	public void setLancamentoDao(LancamentoDao lancamentoDao) {
		this.lancamentoDao = lancamentoDao;
	}
	
}
