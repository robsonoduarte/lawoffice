package br.com.lawoffice.custas.lancamento;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.lawoffice.caixa.CaixaServiceLocal;
import br.com.lawoffice.dominio.Cliente;
import br.com.lawoffice.dominio.Colaborador;
import br.com.lawoffice.dominio.Custa;
import br.com.lawoffice.dominio.Lancamento;
import br.com.lawoffice.persistencia.LancamentoDao;
import br.com.lawoffice.persistencia.PessoaDao;

/**
 * 
 * Teste de unidade para o {@link LancamentoServiceBean}
 * 
 * @author robson
 *
 */
public class LancamentoServiceBeanTest {

	
	
	@Mock
	private CaixaServiceLocal caixaService;
	

	@Mock
	private PessoaDao pessoaDao;
	
	
	@Mock
	private LancamentoDao lancamentoDao;
	
	
	@InjectMocks
	private LancamentoServiceBean lacamentoServiceBean;
	
	

	@Before
	public void setUp() throws Exception {
		lacamentoServiceBean = new LancamentoServiceBean();
		MockitoAnnotations.initMocks(this);
	}

	
	
	@After
	public void tearDown() throws Exception{
		reset(caixaService);
	}	
	
	

	@Test(expected=IllegalArgumentException.class)
	public void deveDispararUmaExcecaoQuandoCustaNula(){
		assertNotNull( 
			lacamentoServiceBean.adicionarCusta(
					null, 
					new Cliente(), 
					new Colaborador(),
					new Date()
				)
			);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void deveDispararUmaExcecaoQuandoClienteNulo(){
		assertNotNull( 
			lacamentoServiceBean.adicionarCusta(
					new Custa(), 
					null, 
					new Colaborador(),
					new Date()
				)
			);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void deveDispararUmaExcecaoQuandoClienteIDNulo(){
		
		Cliente cliente = new Cliente();
		
		
		assertNotNull( 
			lacamentoServiceBean.adicionarCusta(
					new Custa(), 
					cliente, 
					new Colaborador(),
					new Date()
				)
			);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void deveDispararUmaExcecaoQuandoClienteNaoEncontrado(){
		
		Cliente cliente = new Cliente();
		cliente.setId(1l);
		
		Colaborador colaborador = new Colaborador();
		colaborador.setId(1l);
		
		
		when(
			pessoaDao.localizar(Cliente.class, cliente)
		).thenReturn(null);
		
		
		assertNotNull( 
			lacamentoServiceBean.adicionarCusta(
					new Custa(), 
					cliente, 
					colaborador,
					new Date()
				)
			);
	}	
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void deveDispararUmaExcecaoQuandoColaboradorNulo(){
		
		// dados para passar na regra de validação do cliente
		Cliente cliente = new Cliente();
		cliente.setId(1L);
		
		when(
				pessoaDao.localizar(Cliente.class, cliente)
			).thenReturn(cliente);		
		
		
		assertNotNull( 
			lacamentoServiceBean.adicionarCusta(
					new Custa(), 
					cliente, 
					null,
					new Date()
				)
			);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void deveDispararUmaExcecaoQuandoColaboradorIDNulo(){
		
		// dados para passar na regra de validação do cliente
		Cliente cliente = new Cliente();
		cliente.setId(1L);
		
		when(
				pessoaDao.localizar(Cliente.class, cliente)
			).thenReturn(cliente);
		
		
		assertNotNull( 
			lacamentoServiceBean.adicionarCusta(
					new Custa(), 
					cliente, 
					new Colaborador(),
					new Date()
				)
			);
	}
	
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void deveDispararUmaExcecaoQuandoColaboradorNaoEncontrado(){
		
		// dados para passar na regra de validação do cliente
		Cliente cliente = new Cliente();
		cliente.setId(1L);
		
		when(
				pessoaDao.localizar(Cliente.class, cliente)
			).thenReturn(cliente);
		
		// dados do colaborador
		Colaborador colaborador = new Colaborador();
		colaborador.setId(1l);
		
		
		when(
			pessoaDao.localizar(Colaborador.class, colaborador)
		).thenReturn(null);
		
		
		assertNotNull( 
			lacamentoServiceBean.adicionarCusta(
					new Custa(), 
					cliente,
					colaborador,
					new Date()
				)
			);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void deveDispararUmaExcecaoQuandoDataNula(){
		
		// dados para passar na regra de validação do cliente
		Cliente cliente = new Cliente();
		cliente.setId(1L);
		
		when(
				pessoaDao.localizar(Cliente.class, cliente)
			).thenReturn(cliente);
		
		// dados para passar na regra de vailidação do colaborador
		Colaborador colaborador = new Colaborador();
		colaborador.setId(1l);
		
		
		when(
			pessoaDao.localizar(Colaborador.class, colaborador)
		).thenReturn(colaborador);
		
		
		assertNotNull( 
			lacamentoServiceBean.adicionarCusta(
					new Custa(), 
					cliente,
					colaborador,
					null
				)
			);
	}	
	
	
	
	

	@Test
	public void deveRetornaUmaCustaQuandoParmentrosValidos(){
		// dados para passar na regra de validação do cliente
		Cliente cliente = new Cliente();
		cliente.setId(1L);
		
		when(
				pessoaDao.localizar(Cliente.class, cliente)
			).thenReturn(cliente);
		
		// dados para passar na regra de vailidação do colaborador
		Colaborador colaborador = new Colaborador();
		colaborador.setId(1l);
		
		
		when(
			pessoaDao.localizar(Colaborador.class, colaborador)
		).thenReturn(colaborador);
		
		
		assertNotNull( 
			lacamentoServiceBean.adicionarCusta(
					new Custa(), 
					cliente, 
					colaborador,
					new Date()
				)
			);		

	}

	
	
		
	
	@Test
	public void deveRetornaUmaCustaComLancamentoQuandoParmentrosValidos(){
		
		// dados para passar na regra de validação do cliente
		Cliente cliente = new Cliente();
		cliente.setId(1L);
		
		when(
				pessoaDao.localizar(Cliente.class, cliente)
			).thenReturn(cliente);
		
		// dados para passar na regra de vailidação do colaborador
		Colaborador colaborador = new Colaborador();
		colaborador.setId(1l);
		
		
		when(
			pessoaDao.localizar(Colaborador.class, colaborador)
		).thenReturn(colaborador);
		
		
		assertNotNull( 
			lacamentoServiceBean.adicionarCusta(
					new Custa(), 
					cliente, 
					colaborador,
					new Date()
				).getLancamento()
			);
	}
	
	
	
	
			
	/**
	 * 
	 * Deve adicionar uma custa a um lancamento ja criado para o cliente, colaborador e data passados. 
	 * 
	 */
	@Test
	public void deveAdicionarCustaNoMesmoLancamento(){
		
		// dados para passar na regra de validação do cliente
		Cliente cliente = new Cliente();
		cliente.setId(1L);
		
		when(
				pessoaDao.localizar(Cliente.class, cliente)
			).thenReturn(cliente);
		
		// dados para passar na regra de vailidação do colaborador
		Colaborador colaborador = new Colaborador();
		colaborador.setId(1l);
		
		
		when(
				pessoaDao.localizar(Colaborador.class, colaborador)
			).thenReturn(colaborador);
		
		
		Date date = new Date();
		
		
		assertSame(
			lacamentoServiceBean.adicionarCusta(
				new Custa(), 
				cliente, 
				colaborador,
				date).getLancamento(), 
			lacamentoServiceBean.adicionarCusta(
				new Custa(), 
				cliente,
				colaborador,
				date).getLancamento()
		);
	}
	
	
	
	
	/**
	 * deve adicionar a custa em outro lançamento quando cliente e colaborador diferentes e datas diferentes
	 */
	@Test
	public void deveAdicionarCustaOutroLancamento(){
		
		// dados para o primeiro lancamento
		Cliente cliente1 = new Cliente();
		cliente1.setId(1L);
		
		when(
				pessoaDao.localizar(Cliente.class, cliente1)
			).thenReturn(cliente1);
		
		
		Colaborador colaborador1 = new Colaborador();
		colaborador1.setId(1l);
		
		
		when(
				pessoaDao.localizar(Colaborador.class, colaborador1)
			).thenReturn(colaborador1);
		
		
		Calendar calendar = Calendar.getInstance();
		

		Lancamento lancamento1 =
				lacamentoServiceBean.adicionarCusta(
					new Custa(),  
					cliente1, 
					colaborador1,
					calendar.getTime()
				).getLancamento(); 
		
		
		
		// dados para o primeiro lancamento
		Cliente cliente2 = new Cliente();
		cliente2.setId(1L);
		
		when(
				pessoaDao.localizar(Cliente.class, cliente2)
			).thenReturn(cliente2);
		
		
		Colaborador colaborador2 = new Colaborador();
		colaborador2.setId(1l);
		
		
		when(
				pessoaDao.localizar(Colaborador.class, colaborador2)
			).thenReturn(colaborador2);
		
		
		 calendar.add(Calendar.DAY_OF_MONTH, -1);
		

		Lancamento lancamento2 =
				lacamentoServiceBean.adicionarCusta(
					new Custa(),  
					cliente2, 
					colaborador2,
					calendar.getTime()
				).getLancamento();
		
		
		assertNotSame(
				lancamento1,
				lancamento2
			);
	}
	
	
	
	
	@Test
	public void deveRetornaTotalLancamento(){
		
		// dados para passar na regra de validação do cliente
		Cliente cliente = new Cliente();
		cliente.setId(1L);
		
		when(
				pessoaDao.localizar(Cliente.class, cliente)
			).thenReturn(cliente);
		
		// dados para passar na regra de vailidação do colaborador
		Colaborador colaborador = new Colaborador();
		colaborador.setId(1l);
		
		
		when(
				pessoaDao.localizar(Colaborador.class, colaborador)
			).thenReturn(colaborador);
		
		
		Date date = new Date();
		
		Custa custa1 = new Custa();
		custa1.setValor(new BigDecimal(22.00));
		
		Custa custa2 = new Custa();
		custa2.setValor(new BigDecimal(10.00));
		
		
		// add custa 1
		lacamentoServiceBean.adicionarCusta(
				custa1,  
				cliente, 
				colaborador,
				date
		);
		
		// add custa 2 no mesmo lançamento		
		assertEquals(
			new BigDecimal(32.00), 
			lacamentoServiceBean.adicionarCusta(
					custa2,  
					cliente, 
					colaborador,
					date
				).getLancamento().getTotal()
		);
		
		
	}
	
	

	@Test(expected=IllegalStateException.class)
	public void deveLancarExcecacaoQuandoNaoTiverLancamentoParaFechar(){		
		lacamentoServiceBean.fecharLacamento();
	}
	
	
	@Test()
	public void deveFecharLancamento(){
		
		
		// dados para um lançamento
		Cliente cliente1 = new Cliente();
		cliente1.setId(1L);
		
		when(
				pessoaDao.localizar(Cliente.class, cliente1)
			).thenReturn(cliente1);
		
		
		Colaborador colaborador1 = new Colaborador();
		colaborador1.setId(1l);
		
		
		when(
				pessoaDao.localizar(Colaborador.class, colaborador1)
			).thenReturn(colaborador1);

		
		Custa custa = new Custa();
		custa.setValor(new BigDecimal(10));
		

		lacamentoServiceBean.adicionarCusta(
			custa,  
			cliente1, 
			colaborador1,
			new Date()
		);		
		
		
		lacamentoServiceBean.fecharLacamento();
	}	

	
			
	@Test(expected=IllegalArgumentException.class)
	public void deveDispararUmaExcecaoQuandoRemoverCustaNula(){
		lacamentoServiceBean.removerCusta(null);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void deveDispararUmaExcecaoQuandoRemoverCustaSemLancamento(){
		lacamentoServiceBean.removerCusta(new Custa());
	}
	
	
	
	
	@Test(expected=IllegalStateException.class)
	public void deveDispararUmaExcecaoQuandoRemoverCustaEOLancamentoNaoEncontratoNaSessao(){
		lacamentoServiceBean.removerCusta(new Custa().addLancamento(new Lancamento()));
	}
	
	
	
	
	@Test()
	public void deveRemoverACustaPassada(){
		
		// dados para um lançamento
		Cliente cliente1 = new Cliente();
		cliente1.setId(1L);
		
		when(
				pessoaDao.localizar(Cliente.class, cliente1)
			).thenReturn(cliente1);
		
		
		Colaborador colaborador1 = new Colaborador();
		colaborador1.setId(1l);
		
		
		when(
				pessoaDao.localizar(Colaborador.class, colaborador1)
			).thenReturn(colaborador1);

		
		Custa custa = new Custa();
		custa.setValor(new BigDecimal(10));
		

		lacamentoServiceBean.adicionarCusta(
			custa,  
			cliente1, 
			colaborador1,
			new Date()
		);
		
		// removendo a custa do lançamento
		lacamentoServiceBean.removerCusta(custa);
		 
		// lançamento sem custas
		assertEquals(0, custa.getLancamento().getCustas().size());
	}
	

}
