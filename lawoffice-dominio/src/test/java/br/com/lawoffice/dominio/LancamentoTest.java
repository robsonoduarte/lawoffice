package br.com.lawoffice.dominio;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *  
 * 
 * @author robson
 *
 */
public class LancamentoTest {

	private Lancamento lancamentoTest;
	
	@Before
	public void setUp() throws Exception {
		lancamentoTest = new Lancamento();
	}

	@After
	public void tearDown() throws Exception {
		lancamentoTest = null;
	}

	@Test
	public void testGetTotalComListaDeCustaNula(){
		assertEquals(new BigDecimal(0.0), lancamentoTest.getTotal());
	}
	
	
	@Test
	public void testGetTotalComListaDeCustaVazia(){
		lancamentoTest.setCustas(new ArrayList<Custa>());
		assertEquals(new BigDecimal(0.0), lancamentoTest.getTotal());
	}
	
	@Test
	public void testGetTotalComCustas(){
		
		List<Custa> custas = new ArrayList<Custa>();
		
		Custa c1 = new Custa();
		c1.setValor(new BigDecimal(2.0));
		
		Custa c2 = new Custa();
		c2.setValor(new BigDecimal(2.0));		
		
		custas.add(c1);
		custas.add(c2);
		
		
		lancamentoTest.setCustas(custas);
		
		assertEquals(new BigDecimal(4.0), lancamentoTest.getTotal());
	}

}
