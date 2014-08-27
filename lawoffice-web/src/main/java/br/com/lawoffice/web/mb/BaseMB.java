/**
 * 
 */
package br.com.lawoffice.web.mb;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


/**
 * 
 * Classe base para os Manager Beans do projeto.
 * 
 * @author robson
 *
 */
public class BaseMB implements Serializable{

	/**
	 * serial version uid da classe.
	 */
	private static final long serialVersionUID = -2737478216055226686L;


	/**
	 * adiciona uma mensagem do tipo de informação.
	 * 
	 * @param sumario - da mensagem.
	 * @param detalhe - da mensagem.
	 */
	protected void addMsgInformacao(String clienteID, String sumario, String detalhe) {
		adicionarMensagem(clienteID, new FacesMessage(FacesMessage.SEVERITY_INFO, sumario, detalhe));
	}
	
	
	/**
	 * adiciona uma mensagem do tipo de alerta.
	 * 
	 * @param sumario - da mensagem.
	 * @param detalhe - da mensagem.
	 */
	protected void addMsgAlerta(String clienteID, String sumario, String detalhe) {
		adicionarMensagem(clienteID, new FacesMessage(FacesMessage.SEVERITY_WARN, sumario, detalhe));
	}
	
	
	/**
	 * adiciona uma mensagem do tipo de erro.
	 * 
	 * @param sumario - da mensagem.
	 * @param detalhe - da mensagem.
	 */
	protected void adicionarMensagemErro(String clienteID, String sumario, String detalhe) {
		adicionarMensagem(clienteID, new FacesMessage(FacesMessage.SEVERITY_ERROR, sumario, detalhe));
	}
	
	/**
	 * adiciona uma mensagem do tipo de erro para campos obrigatórios.
	 * 
	 * @param sumario - da mensagem.
	 * @param detalhe - da mensagem.
	 */	protected void adicionarMensagemErroValidacao(String clienteID, String detalhe) {
		adicionarMensagem(clienteID, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Existem campos obrigatórios sem preenchimento..: ", detalhe));
	}	
	
	
	/**
	 * adiciona um mensgem no contexto da aplicacao.
	 * 
	 * @param clienteID 
	 * @param facesMessage
	 */
	private void adicionarMensagem(String clienteID, FacesMessage facesMessage){
		FacesContext.getCurrentInstance().addMessage(clienteID,facesMessage);
	}
	
}
