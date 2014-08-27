package br.com.lawoffice.web.mb.caixa;

import java.io.ByteArrayInputStream;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang.time.DateFormatUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.lawoffice.caixa.extrato.ExtratoDTO;
import br.com.lawoffice.caixa.extrato.ExtratoServiceLocal;
import br.com.lawoffice.caixa.extrato.TipoExtrato;
import br.com.lawoffice.dominio.Cliente;
import br.com.lawoffice.dominio.Colaborador;
import br.com.lawoffice.dominio.Custa;
import br.com.lawoffice.web.mb.AutoCompleteMB;

/**
 * 
 * Manager Bean para página de /caixa/extrato.xhtml
 * 
 * @author robson
 *
 */
@ManagedBean
@ViewScoped
public class ExtratoMB extends AutoCompleteMB{

	
	/**
	 * serial version uid do mb.
	 */
	private static final long serialVersionUID = 5304123152213392231L;


	/**
	 *	DTO representando o resultado da pesquisa.
	 */
	private ExtratoDTO extratoDTO;

	
	/**
	 * Data inicial para filtrar a pesquisa do extrato.
	 */
	private Date dataInicial;
	
	
	/**
	 * Data final para filtrar a pesquisa do extrato.
	 */
	private Date dataFinal;
	
	
	/**
	 * Arquivo para dowload do .pdf do extrado do resultado da pesquisa.
	 */
	private StreamedContent fileExtrado;
	
	
	/**
	 * serviços de extrato de {@link Custa}
	 */
	@EJB
	private ExtratoServiceLocal extratoService;
	
	
	
	/**
	 * Pesquisa o Extrato para o {@link Cliente} no periodo passado. 
	 */
	public void pesquisarExtratoCliente(){
		extratoDTO =
				extratoService.getExtratoCliente(dataInicial, dataFinal, cliente);
		
		if(extratoDTO.getItensExtrato().isEmpty()){
			showMsgAlerta();			
		}else{			
			fileExtrado = new DefaultStreamedContent(
					new ByteArrayInputStream(extratoService.gerarExtrato(TipoExtrato.PDF)), 
					"application/pdf", 
					getNomeArquivo()
				);			
		}
	}
	
	/**
	 * Pesquisa o Extrato para o {@link Colaborador} no periodo passado. 
	 */
	public void pesquisarExtratoColaborador(){
		extratoDTO =
				extratoService.getExtratoColaborador(dataInicial, dataFinal, colaborador);
		
		if(extratoDTO.getItensExtrato().isEmpty()){
			showMsgAlerta();			
		}
	}

	
	
	/**
	 * Retorna o nome do arquivo formatado
	 * 
	 * @return {@link String}
	 */
	private String getNomeArquivo() {		
		return new StringBuffer(cliente.getNome().replace(" ", "-"))
			.append("-")
			.append(DateFormatUtils.format(dataInicial, "dd/MM/yyyy"))
			.append("-")
			.append("A")
			.append("-")
			.append(DateFormatUtils.format(dataFinal, "dd/MM/yyyy"))
			.append(".pdf")
			.toString();
	}

	
	/**
	 * exibi msg de alerta.
	 */
	private void showMsgAlerta() {
		addMsgAlerta(
				null, 
				null, 
				"Não foi encontrado lançamentos para esse periodo"
			);
	}	
	
	

	// >>> GETS e SETS do MB <<<

	public Date getDataInicial() {
		return dataInicial;
	}


	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}


	public Date getDataFinal() {
		return dataFinal;
	}


	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}


	public ExtratoDTO getExtratoDTO() {
		return extratoDTO;
	}

	
	public void setExtratoDTO(ExtratoDTO extratoDTO) {
		this.extratoDTO = extratoDTO;
	}


	public StreamedContent getFileExtrado() {
		return fileExtrado;
	}



	public void setFileExtrado(StreamedContent fileExtrado) {
		this.fileExtrado = fileExtrado;
	}
}
