/**
 *
 */
package br.com.lawoffice.web.mb.caixa;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import br.com.lawoffice.caixa.CaixaServiceLocal;
import br.com.lawoffice.dominio.Cliente;
import br.com.lawoffice.dominio.Colaborador;
import br.com.lawoffice.dominio.Conta;
import br.com.lawoffice.web.mb.AutoCompleteMB;

/**
 *
 * Manager Bean para página de /caixa/credito.xhtml
 *
 * @author rduarte
 *
 *
 */

@ManagedBean
public class CreditoMB extends AutoCompleteMB{

	/**
	 * serial version uid da classe.
	 */
	private static final long serialVersionUID = 5082283090828796781L;


	/**
	 * Valor para creditar na {@link Conta} do {@link Cliente} ou do {@link Colaborador}
	 */
	protected BigDecimal valor;


	/**
	 * Serviço de caixa para realizar o credito
	 */
	@EJB
	protected CaixaServiceLocal caixaService;


	/**
	 * realiza o credito na {@link Conta} do {@link Cliente} no valor passado.
	 */
	public void creditarCliente(){
		Conta conta =
			caixaService.creditar(cliente.getConta(), valor, new Date());

		addMsgCreditoSucesso(conta);
	}

	/**
	 * realiza o creidto na {@link Conta} do {@link Colaborador} no valor passado.
	 */
	public void creditarColaborador(){
		Conta conta =
			caixaService.creditar(colaborador.getConta(), valor, new Date());

		addMsgCreditoSucesso(conta);
	}


	/**
	 * add msg de credito realizado com sucesso.
	 *
	 * @param conta - que foi realizado o credito.
	 */
	private void addMsgCreditoSucesso(Conta conta) {
		addMsgInformacao(
				null,
				"Crédito realizado com sucesso: ",
				"Saldo Atual =  "  + NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(conta.getSaldo())
			);
	}



	// >>>> GETS E SETS do MB <<<<

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
}
