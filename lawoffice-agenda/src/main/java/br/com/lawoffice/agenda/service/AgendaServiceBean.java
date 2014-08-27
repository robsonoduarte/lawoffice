/**
 * 
 */
package br.com.lawoffice.agenda.service;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.lawoffice.dominio.Colaborador;
import br.com.lawoffice.dominio.Evento;
import br.com.lawoffice.persistencia.EventoDao;

/**
 * 
 * Implentação do serviço de {@link AgendaService} utilizando tecnologia EJB 3.1.
 * 
 * @author robson
 *
 */
@Stateless
@Local(AgendaServiceLocal.class)
@Remote(AgendaServiceRemote.class)
public class AgendaServiceBean implements AgendaService {

	
	
	// TODO: javado doc na classe e na interface
	
	@EJB
	private EventoDao enventoDao;
	
	
	
	
	@Override
	public List<Evento> listarEventos(Colaborador colaborador, Date dataIncial, Date dataFinal){
		
		validarColaborador(colaborador);
		
		validarDatas(dataIncial,dataFinal);
		
	
		return enventoDao.getEventos(colaborador, dataIncial, dataFinal);
	}



	@Override
	public Evento adicionarEvento(Colaborador colaborador, Evento evento) {		
		validarColaborador(colaborador);
		validarEvento(evento);
		evento.setAgenda(colaborador.getAgenda());
		return enventoDao.salvar(evento);
	}



	@Override
	public Evento atualizarEvento(Evento evento){
		validarEvento(evento);
		return enventoDao.atualizar(evento);
	}

	
	
	private void validarColaborador(Colaborador colaborador) {
		if(colaborador == null)
			throw new IllegalArgumentException("colaborador esta nulo");		
	}
	
	
	private void validarDatas(Date dataIncial, Date dataFinal) {
		if(dataIncial == null)
			throw new IllegalArgumentException("Data inicial está nula");
		if(dataFinal == null)
			throw new IllegalArgumentException("Data Final está nula");
		
	}
	

	private void validarEvento(Evento evento) {
		if(evento == null)
			throw new IllegalArgumentException("Evento está nulo");		
	}		
	
	
	public void setEnventoDao(EventoDao enventoDao) {
		this.enventoDao = enventoDao;
	}
	
}


