/**
 * 
 */
package br.com.lawoffice.web.mb.agenda;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.ScheduleEntrySelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import br.com.lawoffice.agenda.service.AgendaServiceLocal;
import br.com.lawoffice.dominio.Agenda;
import br.com.lawoffice.dominio.Colaborador;
import br.com.lawoffice.dominio.Evento;
import br.com.lawoffice.web.mb.AutoCompleteMB;

/**
 * 
 * Manager Bean para página de /caixa/credito.xhtml
 * 
 * @author rduarte
 *
 */

@ManagedBean
@ViewScoped
public class AgendaMB extends AutoCompleteMB{

	
	/**
	 * 
	 */
	private ScheduleModel scheduleModel;
	
	
	
	
	@EJB
	private AgendaServiceLocal agendaService;
	
	

	
	private EventoAdapter eventoAdapter;
	
	
	
	@PostConstruct
	public void init(){
		
		scheduleModel = new LazyScheduleModel(){

			@Override
			public void loadEvents(Date start, Date end) {
			
				List<DefaultScheduleEvent> scheduleEvents =
					getEventos(start,end);
				
				for (DefaultScheduleEvent defaultScheduleEvent : scheduleEvents) {
					addEvent(defaultScheduleEvent);
				}
			}
		};
		
		/*evento = new EventoTemp();*/
	}

	
	
	
	private List<DefaultScheduleEvent> getEventos(Date dataIncial, Date dataFinal) {
	
		
		Colaborador c = new Colaborador();
		c.setId(3L);
		
		List<Evento> listEventos = null;
		
		try {
			listEventos = agendaService.listarEventos(c, dataIncial, dataFinal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		List<DefaultScheduleEvent> eventos = 
				new ArrayList<DefaultScheduleEvent>();
		
		
		for (Evento evento : listEventos) {
			
				eventos.add(
					new EventoAdapter(
						evento.getTitulo(), 
						evento.getDataIncial(), 
						evento.getDataFinal(), 
						false,
						evento.getId(),
						evento.getAgenda().getId()
					)
				);
		}
		return eventos;
	}

	
	
	
	
	
	
    public void onDataSelecionada(DateSelectEvent dateSelectEvent) {
    	eventoAdapter = 
    		new EventoAdapter(
    				null, 
    				dateSelectEvent.getDate(), 
    				dateSelectEvent.getDate(), 
    				false, 
    				null,
    				null
    			);
    }  
 
	
	
	
	   
	public void onEventoSelecionado(ScheduleEntrySelectEvent selectEvent) {
		eventoAdapter = 
			(EventoAdapter) selectEvent.getScheduleEvent();
    } 
	
	
	
	
	public void onEventoMovido(ScheduleEntryMoveEvent event) {
		
		
		eventoAdapter = 
				(EventoAdapter) event.getScheduleEvent();
		
		
		Calendar c = Calendar.getInstance();
		c.setTime(eventoAdapter.getEndDate());
		c.add(Calendar.DAY_OF_MONTH, event.getDayDelta());
		
		
		eventoAdapter.setEndDate(c.getTime());
		eventoAdapter.setStartDate(c.getTime());
		
		agendaService.atualizarEvento(eventoAdapter.getEvento());
    }  
    
	
	
    public void onEventoRedimensionado(ScheduleEntryResizeEvent event) {
    	
    	System.out.println(event);
    	
    	
/*        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());  
          
        addMessage(message);  */
    }	
	
	
	
	
	
	
	
	public void salvarEvento(){
		
		Colaborador c = new Colaborador();
		c.setId(3l);
		
		Agenda g = new Agenda();
		g.setId(2L);
		
		c.setAgenda(g);
		Evento e = 
				eventoAdapter.getEvento();
		
		if(e.getId() == null){
			agendaService.adicionarEvento(c, eventoAdapter.getEvento());			
		}else{
			agendaService.atualizarEvento(e);
		}
	}
	
	
	
	
	
	
	/**
	 * 
	 * Evento adpater para o {@link Evento} com o {@link ScheduleEvent} do prime faces. 
	 * 
	 * @author rduarte
	 *
	 */
	public class EventoAdapter extends DefaultScheduleEvent{
		
		public Long idEvento;
		public Long idAgenda;
		

		public EventoAdapter(String title, Date start, Date end, boolean allDay, Long idEvento, Long idAgenda) {
			super(title, start, end, allDay);
			this.idEvento = idEvento;
			this.idAgenda = idAgenda;
		}


		public Evento getEvento() {
			Evento evento = 
				new Evento(
					getTitle(), 
					getStartDate(), 
					getEndDate()
				);

			Agenda agenda = new Agenda();
			agenda.setId(getIdAgenda());
			
			evento.setId(getIdEvento());
			evento.setAgenda(agenda);
	
			return evento; 
		}


		public Long getIdEvento() {
			return idEvento;
		}



		public void setIdEvento(Long idEvento) {
			this.idEvento = idEvento;
		}


		public Long getIdAgenda() {
			return idAgenda;
		}


		public void setIdAgenda(Long idAgenda) {
			this.idAgenda = idAgenda;
		}
		
	}
	

	
	
	
	
	
	
	//////////////////////////////////////////////
	
	
	
	public ScheduleModel getScheduleModel() {
		return scheduleModel;
	}


	public void setScheduleModel(ScheduleModel scheduleModel) {
		this.scheduleModel = scheduleModel;
	}




	public EventoAdapter getEventoAdapter() {
		return eventoAdapter;
	}


	public void setEventoAdapter(EventoAdapter eventoAdapter) {
		this.eventoAdapter = eventoAdapter;
	}

}
