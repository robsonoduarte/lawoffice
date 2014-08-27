/**
 * 
 */
package br.com.lawoffice.web.mb.dados;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.lawoffice.dados.PessoaServiceLocal;
import br.com.lawoffice.dominio.Agenda;
import br.com.lawoffice.dominio.Colaborador;
import br.com.lawoffice.dominio.Conta;
import br.com.lawoffice.dominio.Pessoa;
import br.com.lawoffice.web.mb.BaseMB;

/**
 * 
 * Manager Bean para página de /dados/colaborador.xhtml
 * 
 * @author rduarte
 *
 */
@ManagedBean
@ViewScoped
public class ColaboradorMB extends BaseMB {

	
	/**
	 * serial version uid do mb.
	 */
	private static final long serialVersionUID = 8501097824652676049L;


	/**
	 * Colaborador para adicionar/remover/editar 
	 */
	private Colaborador colaborador;
	
	
	/**
	 * Colaborador selecionado na lista de cliente da página ( grid ) 
	 */
	private Colaborador colaboradorSelecionado;
	
	
	/**
	 * Lista de Colaboradores cadastrado no sistema
	 */
	private List<Colaborador> listColaboradores;
	
	
	/**
	 * Serviço de dados para {@link Pessoa} que estao no dominio do escritorio.
	 */
	@EJB
	private PessoaServiceLocal pessoaServiceLocal;
	
	
	/**
	 * inicializa os objetos necessario do mb no contexto JSF. 
	 */
	@PostConstruct
	public void init(){
		listarColaboradores();
		colaborador = new Colaborador();
	}
	
	
	/**
	 * persiste um {@link Colaborador} na base de dados.
	 */
	public void adicionarColaborador(){
		pessoaServiceLocal.salvar(colaborador);
		listarColaboradores();
	}
	
	
	/**
	 * atualizar um {@link Colaborador} na base de dados.
	 */
	public void atualizarColaborador(){
		pessoaServiceLocal.atualizar(colaborador);
	}
	
	
	/**
	 * remove o {@link Colaborador} da base de dados.
	 */
	public void removerColaborador(){
		if(colaboradorSelecionado != null){
			pessoaServiceLocal.remover(Colaborador.class, colaboradorSelecionado);
			listarColaboradores();
		}			
	}
	
	
	/**
	 * recupera o {@link Colaborador} selecionado para edicao.
	 */
	public void editarColaborador(){
		if(colaboradorSelecionado != null)
			colaborador = colaboradorSelecionado;
	}
	
	
	/**
	 * cria um novo {@link Colaborador} com o seus relacionamentos obrigatorios.
	 */
	public void novoColaborador(){
		colaborador = new Colaborador();
		Conta conta = new Conta();
		conta.setSaldo(new BigDecimal(0.0));
		colaborador.setConta(conta);
		conta.setColaborador(colaborador);
		Agenda agenda = new Agenda();
		colaborador.setAgenda(agenda);
		agenda.setColaborador(colaborador);
	}
	
	
	/**
	 * lista todos os {@link Colaborador} do escritorio.
	 */
	private void listarColaboradores() {
		listColaboradores = pessoaServiceLocal.listar(Colaborador.class);
	}

	
	
	
    // >>>>>>> GETS E SETS do MB <<<<<<<<<<<<<
	
	public Colaborador getColaborador() {
		return colaborador;
	}


	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}


	public Colaborador getColaboradorSelecionado() {
		return colaboradorSelecionado;
	}


	public void setColaboradorSelecionado(Colaborador colaboradorSelecionado) {
		this.colaboradorSelecionado = colaboradorSelecionado;
	}


	public List<Colaborador> getListColaboradores() {
		return listColaboradores;
	}


	public void setListColaboradores(List<Colaborador> listColaboradores) {
		this.listColaboradores = listColaboradores;
	}	

}
