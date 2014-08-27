package br.com.lawoffice.caixa.extrato;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.lawoffice.dominio.Cliente;
import br.com.lawoffice.dominio.Colaborador;
import br.com.lawoffice.dominio.Conta;
import br.com.lawoffice.dominio.Custa;
import br.com.lawoffice.dominio.HistoricoConta;
import br.com.lawoffice.dominio.Lancamento;
import br.com.lawoffice.dominio.TipoTransacao;
import br.com.lawoffice.persistencia.ClienteDao;
import br.com.lawoffice.persistencia.ColaboradorDao;
import br.com.lawoffice.persistencia.HistoricoContaDao;
import br.com.lawoffice.persistencia.LancamentoDao;

/**
 *
 * Classe de teste de unidade para o {@link ExtratoServiceBean}.
 *
 * @author rduarte
 *
 */
public class ExtratoServiceBeanTest {


	@Mock
	private ColaboradorDao colaboradorDao;

	@Mock
	private ClienteDao clienteDao;

	@Mock
	private HistoricoContaDao historicoContaDao;


	@Mock
	private LancamentoDao lancamentoDao;


	@Mock
	private FactoryExtratoReport factoryExtratoReport;


	@InjectMocks
	private ExtratoServiceBean extratoServiceBean;



	@Before
	public void setUp() throws Exception {
		extratoServiceBean = new ExtratoServiceBean();
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
		extratoServiceBean  = null;
	}



	@Test(expected=IllegalArgumentException.class)
	public void deveDispararUmaExcecaoQuandoDataInicialNulaExtratoColaborador() {
		extratoServiceBean.getExtratoColaborador(null, new Date(), new Colaborador());
	}



	@Test(expected=IllegalArgumentException.class)
	public void deveDispararUmaExcecaoQuandoDataFinalNulaExtratoColaborador() {
		extratoServiceBean.getExtratoColaborador(new Date(), null, new Colaborador());
	}




	@Test(expected=IllegalArgumentException.class)
	public void deveDispararUmaExcecaoQuandoColaboradorNulaExtratoColaborador() {
		extratoServiceBean.getExtratoColaborador(new Date(), new Date(), null);
	}




	@Test(expected=IllegalArgumentException.class)
	public void deveDispararUmaExcecaoQuandoColaboradorSemIDExtratoColaborador() {
		extratoServiceBean.getExtratoColaborador(new Date(), new Date(), new Colaborador());
	}


	@Test(expected=IllegalArgumentException.class)
	public void deveDispararUmaExcecaoQuandoDataInicialNulaExtratoCliente() {
		extratoServiceBean.getExtratoCliente(null, new Date(), new Cliente());
	}



	@Test(expected=IllegalArgumentException.class)
	public void deveDispararUmaExcecaoQuandoDataFinalNulaExtratoCliente() {
		extratoServiceBean.getExtratoCliente(new Date(), null, new Cliente());
	}



	@Test(expected=IllegalArgumentException.class)
	public void deveDispararUmaExcecaoQuandoClienteNuloExtratoCliente() {
		extratoServiceBean.getExtratoCliente(new Date(), new Date(), null);
	}



	@Test(expected=IllegalArgumentException.class)
	public void deveDispararUmaExcecaoQuandoClienteSemIDExtratoCliente() {
		extratoServiceBean.getExtratoCliente(new Date(), new Date(), new Cliente());
	}






	@Test()
	public void deveRetornaNullQuandoColaboradorNaoCadastrado() {


		Colaborador colaborador = new Colaborador();
		colaborador.setId(1L);


		when(
			colaboradorDao.localizar(Colaborador.class, colaborador)
		).thenReturn(null);


		ExtratoDTO extratoDTO =
			extratoServiceBean.getExtratoColaborador(
					new Date(),
					new Date(),
					colaborador
			);


		assertNull(extratoDTO);
	}


	@Test()
	public void deveRetornaNullQuandoClienteNaoCadastrado() {


		Cliente cliente = new Cliente();
		cliente.setId(1L);


		when(
			clienteDao.localizar(Cliente.class, cliente)
		).thenReturn(null);


		ExtratoDTO extratoDTO =
			extratoServiceBean.getExtratoCliente(
					new Date(),
					new Date(),
					cliente
			);


		assertNull(extratoDTO);
	}



	@Test()
	public void deveRetornaUmExtratoDTOQuandoParametrosValidosExtratoColaborador() {


		// pessoa para ser pesquisada
		Colaborador colaboradorPesquisa = new Colaborador();
		colaboradorPesquisa.setId(1L);


		// colaborador retorno pelo mock do dao de colaborador
		Colaborador colaboradorRetorno = new Colaborador();
		colaboradorRetorno.setNome("Robson Oliveira Duarte");

		// conta do colaborador de retorno do mock
		Conta conta = new Conta();
		conta.setId(1L);
		conta.setSaldo(new BigDecimal(10));
		colaboradorRetorno.setConta(conta);


		// data inicial para consulta.
		Calendar calendar = Calendar.getInstance();
		Date dataInicial = calendar.getTime();


		// data final para consulta.
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date dataFinal = calendar.getTime();


		// data anterior a inicial para consultar saldo anterior
		calendar.add(Calendar.DAY_OF_MONTH, -2);
		Date dataAnterior = calendar.getTime();


		// MASSA DE DADOS PARA TESTE

		// historicos retornados para dataIncial - 1 , para obter o saldo anterior
		HistoricoConta historicoContaDataAnterior1 =
				new HistoricoConta(
						dataAnterior,
						TipoTransacao.CREDITO,
						new BigDecimal(10),
						new BigDecimal(0.0),
						conta
					);


		HistoricoConta historicoContaDataAnterior2 =
				new HistoricoConta(
						dataAnterior,
						TipoTransacao.CREDITO,
						new BigDecimal(10),
						new BigDecimal(10),
						conta
					);

		List<HistoricoConta> historicosContaDataAnterior = new ArrayList<HistoricoConta>();
		historicosContaDataAnterior.add(historicoContaDataAnterior1);
		historicosContaDataAnterior.add(historicoContaDataAnterior2);


		// Lancamentos de custas para o periodo da consulta
		Lancamento lancamentoPeridoConsulta1 = new Lancamento();
		lancamentoPeridoConsulta1.setDataLancamento(dataInicial);

		lancamentoPeridoConsulta1.addCusta(
				new Custa(
					new BigDecimal(10),
					"Natureza 1"
				)
			);

		lancamentoPeridoConsulta1.addCusta(
				new Custa(
					new BigDecimal(10),
					"Natureza 1"
				)
			);

		Lancamento lancamentoPeridoConsulta2 =new Lancamento();
		lancamentoPeridoConsulta2.setDataLancamento(dataFinal);

		lancamentoPeridoConsulta2.addCusta(
				new Custa(
					new BigDecimal(10),
					"Natureza 2"
				)
			);

		lancamentoPeridoConsulta2.addCusta(
				new Custa(
					new BigDecimal(10),
					"Natureza 2"
				)
			);

		List<Lancamento> lancamentosPeriodo =
				new ArrayList<Lancamento>();

		lancamentosPeriodo.add(lancamentoPeridoConsulta1);
		lancamentosPeriodo.add(lancamentoPeridoConsulta2);

		// FIM DA MASSA DE DADOS PARA TESTE

		when(
			  colaboradorDao.localizar(Colaborador.class, colaboradorPesquisa)
			).thenReturn(colaboradorRetorno);



		when(
			 historicoContaDao.getHistoricosConta(dataAnterior, conta)
			).thenReturn(historicosContaDataAnterior);


		when(
			lancamentoDao.getLancamentos(dataInicial,dataFinal,colaboradorRetorno)
		).thenReturn(lancamentosPeriodo);


		ExtratoDTO extratoDTO =
				extratoServiceBean.getExtratoColaborador(dataInicial, dataFinal, colaboradorPesquisa);


		assertNotNull(extratoDTO);
		assertEquals("Robson Oliveira Duarte", extratoDTO.getNomePessoa());
		assertEquals(dataInicial, extratoDTO.getDataInicial());
		assertEquals(dataFinal, extratoDTO.getDataFinal());
		assertEquals(new BigDecimal(10), extratoDTO.getSaldoAnterior());// deve obter o utilmo saldo anterior para a data anterior da data inicial de consulta
		assertEquals(new BigDecimal(10), extratoDTO.getSaldoAtual());
		assertEquals(4, extratoDTO.getItensExtrato().size()); //4 custas de 2 lancamento >> vide massa de dados
		// ordenados pela data de lançamento
		assertEquals(dataInicial, extratoDTO.getItensExtrato().get(0).getDataLancamento());
		assertEquals(dataInicial, extratoDTO.getItensExtrato().get(1).getDataLancamento());
		assertEquals(dataFinal, extratoDTO.getItensExtrato().get(2).getDataLancamento());
		assertEquals(dataFinal, extratoDTO.getItensExtrato().get(3).getDataLancamento());
	}




	@Test()
	public void deveRetornaUmExtratoDTOQuandoParametrosValidosExtratoCliente() {

		// pessoa para ser pesquisada
		Cliente clientePesquisa = new Cliente();
		clientePesquisa.setId(1l);

		// colaborador retorno pelo mock do dao de colaborador
		Cliente clienteRetorno = new Cliente();
		clienteRetorno.setNome("Robson Oliveira Duarte");

		// conta do cliente de retorno do mock
		Conta conta = new Conta();
		conta.setId(1L);
		conta.setSaldo(new BigDecimal(10));
		clienteRetorno.setConta(conta);


		// data inicial para consulta.
		Calendar calendar = Calendar.getInstance();
		Date dataInicial = calendar.getTime();


		// data final para consulta.
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date dataFinal = calendar.getTime();

		// data anterior a inicial para consultar saldo anterior
		calendar.add(Calendar.DAY_OF_MONTH, -2);
		Date dataAnterior = calendar.getTime();


		// MASSA DE DADOS PARA TESTE

		// historicos retornados para dataIncial - 1 , para obter o saldo anterior
		HistoricoConta historicoContaDataAnterior1 =
				new HistoricoConta(
						dataAnterior,
						TipoTransacao.CREDITO,
						new BigDecimal(10),
						new BigDecimal(0.0),
						conta
					);


		HistoricoConta historicoContaDataAnterior2 =
				new HistoricoConta(
						dataAnterior,
						TipoTransacao.CREDITO,
						new BigDecimal(10),
						new BigDecimal(10),
						conta
					);

		List<HistoricoConta> historicosContaDataAnterior = new ArrayList<HistoricoConta>();
		historicosContaDataAnterior.add(historicoContaDataAnterior1);
		historicosContaDataAnterior.add(historicoContaDataAnterior2);



		// Lancamentos de custas para o periodo da consulta
		Lancamento lancamentoPeridoConsulta1 = new Lancamento();
		lancamentoPeridoConsulta1.setDataLancamento(dataInicial);

		lancamentoPeridoConsulta1.addCusta(
				new Custa(
					new BigDecimal(10),
					"Natureza 1"
				)
			);

		lancamentoPeridoConsulta1.addCusta(
				new Custa(
					new BigDecimal(10),
					"Natureza 1"
				)
			);

		Lancamento lancamentoPeridoConsulta2 =new Lancamento();
		lancamentoPeridoConsulta2.setDataLancamento(dataFinal);

		lancamentoPeridoConsulta2.addCusta(
				new Custa(
					new BigDecimal(10),
					"Natureza 2"
				)
			);

		lancamentoPeridoConsulta2.addCusta(
				new Custa(
					new BigDecimal(10),
					"Natureza 2"
				)
			);

		List<Lancamento> lancamentosPeriodo =
				new ArrayList<Lancamento>();

		lancamentosPeriodo.add(lancamentoPeridoConsulta1);
		lancamentosPeriodo.add(lancamentoPeridoConsulta2);


		// FIM DA MASSA DE DADOS PARA TESTE

		when(
			  clienteDao.localizar(Cliente.class, clientePesquisa)
			).thenReturn(clienteRetorno);


		when(
			  historicoContaDao.getHistoricosConta(dataAnterior, conta)
			).thenReturn(historicosContaDataAnterior);


		when(
				lancamentoDao.getLancamentos(dataInicial,dataFinal,clienteRetorno)
			).thenReturn(lancamentosPeriodo);


		ExtratoDTO extratoDTO =
				extratoServiceBean.getExtratoCliente(dataInicial, dataFinal, clientePesquisa);



		assertNotNull(extratoDTO);
		assertEquals("Robson Oliveira Duarte", extratoDTO.getNomePessoa());
		assertEquals(dataInicial, extratoDTO.getDataInicial());
		assertEquals(dataFinal, extratoDTO.getDataFinal());
		assertEquals(new BigDecimal(10), extratoDTO.getSaldoAnterior()); // deve obter o utilmo saldo anterior para a data anterior da data inicial de consulta
		assertEquals(new BigDecimal(10), extratoDTO.getSaldoAtual());
		assertEquals(4, extratoDTO.getItensExtrato().size()); //  4 custas de 2 lancamento >> vide massa de dados
		// ordenados pela data de lançamento/transacao
		assertEquals(dataInicial, extratoDTO.getItensExtrato().get(0).getDataLancamento());
		assertEquals(dataInicial, extratoDTO.getItensExtrato().get(1).getDataLancamento());
		assertEquals(dataFinal, extratoDTO.getItensExtrato().get(2).getDataLancamento());
		assertEquals(dataFinal, extratoDTO.getItensExtrato().get(3).getDataLancamento());


	}



	@Test(expected=IllegalStateException.class)
	public void deveLancarExcecaoQuandoExtratoDTONuloGerarExtrato() {
		extratoServiceBean.gerarExtrato(TipoExtrato.PDF);
	}


	@Test()
	public void deveRetorarUmArrayDebyteQuandoExtratoDTOGeradoGerarExtrato() {

		Cliente cliente = new Cliente();
		cliente.setNome("Robson");
		cliente.setId(1l);
		Conta conta = new Conta();
		conta.setSaldo(new BigDecimal(10));
		cliente.setConta(conta);

		when(
				clienteDao.localizar(Cliente.class, cliente)
			).thenReturn(cliente);


		ExtratoDTO extratoDTO =
				extratoServiceBean.getExtratoCliente(new Date(), new Date(), cliente);


		ExtratoReport extratoReport = mock(ExtratoReport.class);


		when(
			extratoReport.gerarExtrato(extratoDTO)
		).thenReturn(new byte[0]);


		when(
			factoryExtratoReport.createExtratoReport(TipoExtrato.PDF)
		).thenReturn(extratoReport);


		byte[] extrato =
				extratoServiceBean.gerarExtrato(TipoExtrato.PDF);

		assertNotNull(extrato);
	}


}
