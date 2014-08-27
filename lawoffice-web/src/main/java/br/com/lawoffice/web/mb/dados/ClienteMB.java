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
import br.com.lawoffice.dominio.Cliente;
import br.com.lawoffice.dominio.Conta;
import br.com.lawoffice.dominio.Pessoa;
import br.com.lawoffice.web.mb.BaseMB;

/**
 * 
 * Manager Bean para página de /dados/cliente.xhtml
 * 
 * @author rduarte
 *
 */
@ManagedBean
@ViewScoped
public class ClienteMB extends BaseMB {

	
	/**
	 * serial version uid do mb.
	 */
	private static final long serialVersionUID = -6574264805469938663L;


	/**
	 * Cliente para adicionar/remover/editar 
	 */
	private Cliente cliente;
	
	
	/**
	 * Cliente selecionado na lista de cliente da página ( grid ) 
	 */
	private Cliente clienteSelecionado;
	
	
	/**
	 * Lista de Clientes cadastrado no sistema
	 */
	private List<Cliente> listClientes;
	
	
	/**
	 * Serviço de dados para {@link Pessoa} que estao no dominio do escritorio.
	 */
	@EJB
	private PessoaServiceLocal pessoaServiceLocal;
	
	
	/**
	 * inicializa os objetos necessarios do mb no contexto do JSF. 
	 */
	@PostConstruct
	public void init(){
		listarClientes();
		cliente = new Cliente();
	}
	
	
	/**
	 * persiste um {@link Cliente} na base de dados.
	 */
	public void adicionarCliente(){
		pessoaServiceLocal.salvar(cliente);
		listarClientes();
	}
	
	
	/**
	 * atualiza os dados de um {@link Cliente} na base de dados.
	 */
	public void atualizarCliente(){
		pessoaServiceLocal.atualizar(cliente);
	}
	
	
	
	/**
	 * remove um {@link Cliente} da base de dados.
	 */
	public void removerCliente(){
		if(clienteSelecionado != null){			
			pessoaServiceLocal.remover(Cliente.class,clienteSelecionado);
			listarClientes();
		}			
	}
	
	
	/**
	 * recupera o {@link Cliente} selecionado para edicao.
	 */
	public void editarCliente(){
		if(clienteSelecionado != null)
			cliente = clienteSelecionado;
	}
	
	
	/**
	 * cria um novo {@link Cliente} com seu relacionamentos obrigatorios.
	 */
	public void novoCliente(){
		cliente = new Cliente();  
		Conta conta = new Conta();
		conta.setSaldo(new BigDecimal(0.0));
		cliente.setConta(conta);
		conta.setCliente(cliente);
	}
	
	
	/**
	 * lsita todos os {@link Cliente} do escritori.
	 */
	private void listarClientes() {
		listClientes = pessoaServiceLocal.listar(Cliente.class);
	}


    // >>>>>>> GETS E SETS do MB <<<<<<<<<<<<<	
	
	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}


	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}


	public List<Cliente> getListClientes() {
		return listClientes;
	}


	public void setListClientes(List<Cliente> listClientes) {
		this.listClientes = listClientes;
	}
		
}
