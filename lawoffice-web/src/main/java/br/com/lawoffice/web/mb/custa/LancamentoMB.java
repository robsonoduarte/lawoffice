package br.com.lawoffice.web.mb.custa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.lawoffice.custas.lancamento.LancamentoServiceLocal;
import br.com.lawoffice.dominio.Custa;
import br.com.lawoffice.dominio.Lancamento;
import br.com.lawoffice.web.mb.AutoCompleteMB;

/**
 * Manager Bean para página de /custas/lacamento.xhtml
 * 
 * @author robson
 *
 */
@ManagedBean
@ViewScoped 
public class LancamentoMB extends AutoCompleteMB {
	
	
	/**
	 * serial version uid do mb.
	 */
	private static final long serialVersionUID = -3836463046908701559L;


	/**
	 * {@link Custa} para adição/edicão/remoção da lista de custas 
	 */
	private Custa custa;
	
	
	/**
	 * {@link Custa} selecionada para edição e remoção do grid de custa na página
	 */
	private Custa custaSelecionada;
	
	
	/**
	 * Serviço de lançamentos de custas 
	 */
	@EJB
	private LancamentoServiceLocal lancamentoService;

	
	
	/**
	 * Data do {@link Lancamento}
	 */
	private Date dataLancamento;
	

	
	/**
	 * Custas adicionadas para efetuar o lançamento ( montagem da tabela )
	 */
	private List<Custa> custas;
	
	
	
	/**
	 * inicializa os objetos necessario do mb apos sua criacao no contexto JSF. 
	 */
	@PostConstruct
	public void init(){
		custa = new Custa();
		custas = new ArrayList<Custa>();
	}
	
	
	
	
	/**
	 * adiciona um custa ao lancamento.
	 */
	public void adicionarCusta(){		
		custas.add(
			lancamentoService.adicionarCusta(
				custa, 
				cliente,
				colaborador,
				dataLancamento
			)
		);
		custa = new Custa();	
	}
	

	/**
	 * remove uma custa do lancamento.
	 */
	public void removerCusta(){		
		if(custaSelecionada != null){
			lancamentoService.removerCusta(custaSelecionada);
			custas.remove(custaSelecionada);
		}			
	}
	

	
	/**
	 * realiza o fechamento do(s) lancamento(s).
	 */
	public void fecharLancamento(){
		try {
			lancamentoService.fecharLacamento();
			custas.clear();
			addMsgInformacao(
				null, 
				null, 
				"Lançamento fechado com sucesso."
			);
		} catch (IllegalStateException e){
			adicionarMensagemErro(null, null, e.getMessage());
		}
	}


	
	// >>>>>>>> GETS E SETS DO MB <<<<<<<<<<<<<
	
	public Custa getCusta() {
		return custa;
	}

	public void setCusta(Custa custa) {
		this.custa = custa;
	}


	public Custa getCustaSelecionada() {
		return custaSelecionada;
	}


	public void setCustaSelecionada(Custa custaSelecionada) {
		this.custaSelecionada = custaSelecionada;
	}

	public List<Custa> getCustas() {
		return custas;
	}
	
	public void setCustas(List<Custa> custas) {
		this.custas = custas;
	}


	public Date getDataLancamento() {
		return dataLancamento;
	}


	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
}
