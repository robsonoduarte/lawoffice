package br.com.lawoffice.caixa.extrato.jasper;

import br.com.lawoffice.caixa.extrato.ExtratoReport;
import br.com.lawoffice.caixa.extrato.FactoryExtratoReport;
import br.com.lawoffice.caixa.extrato.TipoExtrato;

/**
 * Factory para criação de {@link ExtratoReport} através do seu {@link TipoExtrato} 
 * para implementações que utilizam o Jasper Report 4.0.1 para geração do arquivo de extrato.
 * 
 * @author robson
 *
 */
public class SimpleFactoryExtratoReportJasper implements FactoryExtratoReport{

	
	

	@Override
	public ExtratoReport createExtratoReport(TipoExtrato tipoExtrato) {
		if(tipoExtrato == null)
			throw new IllegalArgumentException("TipoExtrato esta nulo");
		switch (tipoExtrato){
			case PDF:
				return new ExtratoReportJasperPDF();
			default:
				throw new IllegalArgumentException(
						"Factory nao possui suporte para criacao do tipo de extrato :" 
								+ tipoExtrato + 
								"ou nao há implementacao para o tipo de extrato " 
								+ tipoExtrato
							);
		}
	}
}
