/**
 *
 */
package br.com.lawoffice.caixa.extrato.jasper;


import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.com.lawoffice.caixa.extrato.ExtratoDTO;
import br.com.lawoffice.caixa.extrato.ExtratoReport;
import br.com.lawoffice.caixa.extrato.ExtratoReportException;


/**
 *
 * Implementação do {@link ExtratoReport} para geração de PDF utilizando Jasper Report 4.2.
 *
 * @author robson
 *
 */
public class ExtratoReportJasperPDF extends ExtratoReportJasperBase {


	@Override
	public byte[] gerarExtrato(ExtratoDTO extratoDTO) {
		if(extratoDTO == null)
			throw new IllegalArgumentException("Extrato DTO nulo");

		JRBeanCollectionDataSource jrBeanCollectionDataSource =
				new JRBeanCollectionDataSource(extratoDTO.getItensExtrato());
		
		try {

			JasperPrint	jasperPrint =
				JasperFillManager.fillReport(
						this.getClass().getResourceAsStream("/jasper/extrato-custas.jasper"),
						getMapParametros(extratoDTO),
						jrBeanCollectionDataSource
					);
			
			return JasperExportManager.exportReportToPdf(jasperPrint);

		} catch (JRException e){
			throw new ExtratoReportException(e);
		}
	}
}
