/**
 * 
 */
package br.com.lawoffice.dominio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * O {@link Lancamento} possui as {@link Custa}(s) de um {@link Cliente} realizada(s) pelo o {@link Colaborador} em uma determinada data.
 * 
 * @author robson
 *
 */
@Entity
@Table(name="LANCAMENTO")
public class Lancamento implements EntityBase{

	/**
	 *  serial version uid da classe.
	 */
	private static final long serialVersionUID = 3967490166883427717L;

	/**
	 * Identificador na base de dados.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	/**
	 * {@link Cliente} do lancamento.
	 */
	@ManyToOne
	@JoinColumn(name="CLIENTE_ID")
	private Cliente cliente;
	
	
	/**
	 * {@link Colaborador} do lancamento.
	 */
	@ManyToOne
	@JoinColumn(name="COLABORADOR_ID")
	private Colaborador colaborador;
	
	
	/**
	 * Data do lancamento.
	 */
	@Column(name="DATA_LANCAMENTO")
	@Temporal(TemporalType.DATE)
	private Date dataLancamento;
	
	
	
	/**
	 * {@link Custa}s do lancamento. 
	 */
	@OneToMany(mappedBy="lancamento", cascade = CascadeType.ALL)
	private List<Custa> custas;
	
	
	
	// >>>>>>> GETS E SETS <<<<<<<<<<<

	
	public Cliente getCliente() {
		return cliente;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public Colaborador getColaborador() {
		return colaborador;
	}


	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}


	public Date getDataLancamento() {
		return dataLancamento;
	}


	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}


	public List<Custa> getCustas() {
		return custas;
	}


	public void setCustas(List<Custa> custas) {
		this.custas = custas;
	}

	
	// >>>>>>>> MÉTODOS DE DOMINIO <<<<<<<<<<<<<<
	

	/**
	 * Adiciona um {@link Cliente} ao {@link Lancamento}
	 * 
	 * @param cliente - a ser adicionado ao lancamento.
	 * @return {@link Lancamento} com o cliente adicionado.
	 * @throws IllegalArgumentException quando o cliente estiver nulo.
	 */
	
	public Lancamento adicionarCliente(Cliente cliente) {
		if(cliente == null)
			throw new IllegalArgumentException("Cliente esta nulo");		
		setCliente(cliente);
		return this;
	}


	/**
	 * Adiciona um {@link Colaborador} ao {@link Lancamento}
	 * 
	 * @param colaborador - a ser adicionado ao lancamento.
	 * @return {@link Lancamento} com o colaborador adicionado.
	 * @throws IllegalArgumentException quando o colaborador estiver nulo.
	 */
	public Lancamento adicionarColaborador(Colaborador colaborador){
		if(colaborador == null)
			throw new IllegalArgumentException("Colaborador esta nulo");
		setColaborador(colaborador);
		return this;
	}	
	
	
	/**
	 * Adiciona uma {@link Date} para o {@link Lancamento}.
	 * @param data  data do lancamento.
	 * @return {@link Lancamento} com a data.
	 * @throws IllegalArgumentException quando a data estiver nula.
	 */
	public Lancamento adicionarDataLancamento(Date data){		
		if(data == null)
			throw new IllegalArgumentException("Data esta nula");
		setDataLancamento(data);
		return this;
	}

	
	/**
	 * Adicona uma {@link Custa} a lista de custas do {@link Lancamento}.
	 * @param custa a ser adicionada no lancamento. 
	 * @return {@link Lancamento} com a custa adicionada.
	 * @throws IllegalArgumentException quando a custa estiver nula.
	 */
	public Lancamento addCusta(Custa custa){
		if(custa == null)
			throw new IllegalArgumentException("custa esta nula");
		if(custas == null)
			custas = new ArrayList<Custa>();
		custas.add(custa);
		return this;
	}
	
	
	/**
	 * Retorna o total do lançamento.
	 * <br><br>
	 * <b>Obs:</b> Se não houver nenhuma custa retorna um {@link BigDecimal} com valor 0.0
	 * 
	 * @return {@link BigDecimal}
	 */
	public BigDecimal getTotal(){
		BigDecimal total = new BigDecimal(0.0);
		if(custas == null || custas.isEmpty())
			return total;
		
		for (Custa custa : custas)
			total = total.add(custa.getValor());
		
		return total;
	}


	@Override
	public String toString() {
		return "Lancamento [id=" + id + ", cliente=" + cliente
				+ ", colaborador=" + colaborador + ", dataLancamento="
				+ dataLancamento + ", custas=" + custas + "]";
	}
	
}
