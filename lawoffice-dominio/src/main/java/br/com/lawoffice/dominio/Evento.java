/**
 * 
 */
package br.com.lawoffice.dominio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * Representa um evento da {@link Agenda} do {@link Colaborador} do escritório.
 * 
 * 
 * @author robson
 *
 */
@Entity
@Table(name="EVENTO")
public class Evento implements EntityBase{

	/**
	 * serial verion uid da classe.
	 */
	private static final long serialVersionUID = 1687779862606665824L;

	/**
	 * identificador da entidade.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	/**
	 * agenda onde o evento esta atrelado.
	 */
	@ManyToOne
	@JoinColumn(name="AGENDA_ID")
	private Agenda agenda;
	
	/**
	 * titulo do evento.
	 */
	@Column(name = "TITULO")
	private String titulo;
	
	/**
	 * data inicial do evento. 
	 */
	@Column(name = "DATA_INICIAL")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataIncial;
	
	/**
	 * data final do evento.
	 */
	@Column(name = "DATA_FINAL")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFinal;



	public Evento() {
	}



	public Evento(String titulo, Date dataIncial, Date dataFinal) {
		super();
		this.titulo = titulo;
		this.dataIncial = dataIncial;
		this.dataFinal = dataFinal;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Agenda getAgenda() {
		return agenda;
	}



	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}



	public String getTitulo() {
		return titulo;
	}



	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}



	public Date getDataIncial() {
		return dataIncial;
	}



	public void setDataIncial(Date dataIncial) {
		this.dataIncial = dataIncial;
	}



	public Date getDataFinal() {
		return dataFinal;
	}



	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}



	@Override
	public String toString() {
		return "Evento [id=" + id + ", agenda=" + agenda + ", titulo=" + titulo
				+ ", dataIncial=" + dataIncial + ", dataFinal=" + dataFinal
				+ "]";
	}
	
}
