/**
 * 
 */
package br.com.lawoffice.caixa.extrato.jasper;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.lawoffice.caixa.extrato.ExtratoReport;
import br.com.lawoffice.caixa.extrato.TipoExtrato;

/**
 * 
 * Classe de teste de unidade para {@link SimpleFactoryExtratoReportJasper}.
 * 
 * @author rduarte
 *
 */
public class SimpleFactoryExtratoReportJasperTest {

	private SimpleFactoryExtratoReportJasper simpleFactoryExtratoReportJasper;
	

	@Before
	public void setUp() throws Exception {
		simpleFactoryExtratoReportJasper = new SimpleFactoryExtratoReportJasper();
	}


	@After
	public void tearDown() throws Exception {
		simpleFactoryExtratoReportJasper = null;
	}

	@Test()
	public void deveRetornaUmExtratoReportJasperPDF() {
		ExtratoReport extratoReport = 
				simpleFactoryExtratoReportJasper.createExtratoReport(TipoExtrato.PDF);
		
		assertNotNull(extratoReport);
		assertTrue(extratoReport instanceof ExtratoReportJasperPDF);
		
	}	
	

	@Test(expected=IllegalArgumentException.class)
	public void deveDispararUmaExcecaoQuandoTipoExtradoNaoValido() {
		simpleFactoryExtratoReportJasper.createExtratoReport(null);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void deveDispararUmaExcecaoQuandoNaoHouverImplementacaoParaTipoExtrato() {
		simpleFactoryExtratoReportJasper.createExtratoReport(TipoExtrato.HTML);
	}

}
