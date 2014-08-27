package br.com.lawoffice.agenda.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.lawoffice.dominio.Colaborador;
import br.com.lawoffice.dominio.Evento;
import br.com.lawoffice.persistencia.EventoDao;

/**
 * 
 * Teste de unidade para {@link AgendaServiceBean} que implementa {@link AgendaService} utilizando tecnologina EJB 3.1.
 * 
 * 
 * @author robson
 *
 */
public class AgendaServiceBeanTest {

	@Mock
	private EventoDao enventoDao;

	
	@InjectMocks
	private AgendaServiceBean agendaServiceBean;
	

	
	@Before
	public void setUp() throws Exception{
		agendaServiceBean = new AgendaServiceBean();
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
		agendaServiceBean = null;
		reset(enventoDao);
	}

	@Test(expected=IllegalArgumentException.class)
	public void deveLancarExcecaoQuandoColaboradorNuloListarEventos() {
		agendaServiceBean.listarEventos(
				null, 
				new Date(), 
				new Date()
			);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void deveLancarExcecaoQuandoDataInicialNulaListarEventos() {
		agendaServiceBean.listarEventos(
				new Colaborador(), 
				null, 
				new Date()
			);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void deveLancarExcecaoQuandoDataFinalNulaListarEventos() {
		agendaServiceBean.listarEventos(
				new Colaborador(), 
				new Date(), 
				null
			);
	}
	
		
	
	@Test
	public void deveRetornarListaQuandoParamentrosValidosListarEventos() {
		
		Colaborador colaborador = new Colaborador();
		

		List<Evento> eventos = new ArrayList<Evento>();
		eventos.add(new Evento());
		
		Date dataInicial = new Date();
		Date dataFinal = new Date();
		
		when(
			enventoDao.getEventos(colaborador, dataInicial, dataFinal)
		).thenReturn(eventos);
		
		assertTrue(
			!agendaServiceBean.listarEventos(
					colaborador, 
					dataInicial, 
					dataFinal
					).isEmpty()
			);
	}	
		
	
	
	@Test(expected=IllegalArgumentException.class)
	public void deveLancarExcecaoQuandoColaboradorNuloAdicionarEvento() {
		agendaServiceBean.adicionarEvento(null, new Evento());
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void deveLancarExcecaoQuandoEventoNuloAdicionarEvento() {
		agendaServiceBean.adicionarEvento(new Colaborador(), null);
	}	

	
	@Test
	public void deveLancarAdicionarEventoQuandoParamentrosValidos() {
		
		Evento evento = new Evento();
		
		when(
			enventoDao.salvar(evento)
		).thenReturn(evento);
		
		
		evento = 
			agendaServiceBean.adicionarEvento(new Colaborador(), evento);
		
		assertNotNull(evento);
	}	
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public void deverLancarExcecaoQuandoEventoNuloAtualizarEvento() {
		agendaServiceBean.atualizarEvento(null);
	}
	
	
	
	@Test
	public void deverAtualizarEventoQuandoParamentrosValidosAtualizarEvento() {
		
		
		Evento evento = new Evento();
		
		when(
			enventoDao.atualizar(evento)
		).thenReturn(evento);
		
		evento =
			agendaServiceBean.atualizarEvento(evento);
		
		assertNotNull(evento);
	}	

}
