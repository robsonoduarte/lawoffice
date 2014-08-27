package br.com.lawoffice.caixa.extrato.jasper;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;

import br.com.lawoffice.caixa.extrato.ExtratoDTO;
import br.com.lawoffice.caixa.extrato.ExtratoReport;

/**
 *
 * Classe Base para as implementacões do {@link ExtratoReport} utilizando Jasper Report 4.2.
 *
 * @author rduarte
 *
 */
public abstract class ExtratoReportJasperBase implements ExtratoReport{


	/**
	 * Retoran um {@link Map} populado com os paramentros do relatorio.
	 *
	 * @param extratoDTO
	 * @return
	 */
	protected Map<String,Object> getMapParametros(ExtratoDTO extratoDTO) {
		Map<String, Object> paramentros =
				new HashMap<String, Object>();

		NumberFormat numberFormat =
				NumberFormat.getCurrencyInstance(new Locale("pt", "Br"));

		paramentros.put("SALDO_ANTERIOR", numberFormat.format(extratoDTO.getSaldoAnterior()));
		paramentros.put("SALDO_ATUAL", numberFormat.format(extratoDTO.getSaldoAtual()));
		paramentros.put("DATA_INICIAL", DateFormatUtils.format(extratoDTO.getDataInicial(), "dd/MM/yyyy"));
		paramentros.put("DATA_FINAL", DateFormatUtils.format(extratoDTO.getDataFinal(), "dd/MM/yyyy"));
		paramentros.put("NOME_PESSOA", extratoDTO.getNomePessoa());
		paramentros.put("REPORT_LOCALE", new Locale("pt", "BR"));

		return paramentros;
	}
}