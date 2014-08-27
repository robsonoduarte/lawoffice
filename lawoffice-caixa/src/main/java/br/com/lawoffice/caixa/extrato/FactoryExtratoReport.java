package br.com.lawoffice.caixa.extrato;

/**
 * 
 * Factory para criacao de {@link ExtratoReport} conforme o {@link TipoExtrato}.
 * 
 * @author rduarte
 *
 */
public interface FactoryExtratoReport {

	/**
	 * cria o {@link ExtratoReport} conforme o {@link TipoExtrato}.
	 * 
	 * @param tipoExtrato - a ser criado.
	 * @return - {@link ExtratoReport} conforme o {@link TipoExtrato}.
	 */
	ExtratoReport createExtratoReport(TipoExtrato tipoExtrato); 
}
