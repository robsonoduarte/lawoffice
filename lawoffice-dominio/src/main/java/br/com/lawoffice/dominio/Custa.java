package br.com.lawoffice.dominio;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * A Custa esta ligada ao serviço de um {@link Colaborador} e despesa que o escritório tem com um {@link Cliente} e estão 
 * 
 * ligadas diretamentee a um {@link Lancamento}.
 * 
 * @author robson
 *
 */
@Entity
@Table(name="CUSTA")
public class Custa implements EntityBase{

	/**
	 * serial version uid da entidade.
	 * 
	 */
	private static final long serialVersionUID = 4857927343419332646L;

	/**
	 * identificador da entidade.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	/**
	 * valor da custa.
	 */
	@Column(name="VALOR", precision = 19 , scale = 2)
	private BigDecimal valor;
	
	/**
	 * natureza da custa.
	 */
	@Column(name="NATUREZA")
	private String natureza;

	
	/**
	 * lancamento que a custa esta ligada.
	 */
	@ManyToOne
	@JoinColumn(name="LANCAMENTO_ID")
	private Lancamento lancamento;
	

	
	public Custa() {
	}
	
	
	
	public Custa(BigDecimal valor, String natureza) {
		super();
		this.valor = valor;
		this.natureza = natureza;
	}

	
	
	// 	>>>>>>>> MÉTODOS DE DOMINIO <<<<<<<<<
	public Custa addLancamento(Lancamento lancamento) {
		setLancamento(lancamento);
		return this;
	}
	

	
	// >>>>>>>>>> GETS E SET <<<<<<<<<<<<<<<<<

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}
	
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getNatureza() {
		return natureza;
	}

	public void setNatureza(String natureza) {
		this.natureza = natureza;
	}


	public Lancamento getLancamento() {
		return lancamento;
	}


	public void setLancamento(Lancamento lacamento) {
		this.lancamento = lacamento;
	}

	@Override
	public String toString() {
		return "Custa [id=" + id + ", valor=" + valor + ", natureza="
				+ natureza + ", lancamento=" + lancamento + "]";
	}
}
