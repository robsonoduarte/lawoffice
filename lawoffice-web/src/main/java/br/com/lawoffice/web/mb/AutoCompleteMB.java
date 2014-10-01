package br.com.lawoffice.web.mb;

import java.util.List;

import javax.ejb.EJB;

import br.com.lawoffice.dados.PessoaServiceLocal;
import br.com.lawoffice.dominio.Cliente;
import br.com.lawoffice.dominio.Colaborador;
import br.com.lawoffice.dominio.Pessoa;
import br.com.lawoffice.web.mb.caixa.CreditoMB;
import br.com.lawoffice.web.mb.caixa.DebitoMB;
import br.com.lawoffice.web.mb.custa.LancamentoMB;

/**
 * 
 * Manager Bean base para os manager bean que possui recurso de auto complete
 * 
 * @author robson
 *
 * @see {@link DebitoMB} {@link CreditoMB} {@link LancamentoMB}
 *
 */
public class AutoCompleteMB extends BaseMB{
	
	
	/**
	 * serial version uid da classe.
	 */
	private static final long serialVersionUID = 1166161353799699898L;


	/**
	 * Serviço de dados cadastrais de {@link Pessoa} para realizar o autoComplete 
	 */
	@EJB
	private PessoaServiceLocal pessoaServiceLocal;
	
	
	/**
	 * {@link Cliente} selecionado no autocomplete
	 */
	protected Cliente cliente;
	
	
	/**
	 * {@link Colaborador} selecionado no autocomplete 
	 */
	protected Colaborador colaborador;
	
	
	
	/**
	 * lista os {@link Cliente}(s) utilizando o nome como paramentro para filtro.
	 * 
	 * @param nome - nome do cliente.
	 * @return {@link List} de {@link Cliente} com o nome passsado.
	 */ 
	public List<Cliente> listarClientes(String nome){
		return pessoaServiceLocal.listarPorNome(Cliente.class, nome); 
	}
	
	/**
	 * lista os {@link Colaborador}(s) utilizando o neom como paramentro para filtro.
	 * 
	 * @param nome - nome do cliente.
	 * @return {@link List} de {@link Colaborador} com o nome passado.
	 */
	public List<Colaborador> listarColaboradores(String nome){
		return pessoaServiceLocal.listarPorNome(Colaborador.class, nome); 
	}
	

	// >>>>>>> GETS & SETS <<<<<<<<<<<
	

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}	
	
}
