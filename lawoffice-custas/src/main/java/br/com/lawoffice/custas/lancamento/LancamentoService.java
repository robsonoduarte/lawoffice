package br.com.lawoffice.custas.lancamento;

import java.util.Date;

import br.com.lawoffice.dominio.Cliente;
import br.com.lawoffice.dominio.Colaborador;
import br.com.lawoffice.dominio.Custa;
import br.com.lawoffice.dominio.Lancamento;



/**
 * interface para os serviço de laçamentos de custa.
 * 
 * @author robson
 *
 */
public interface LancamentoService{

	/**
	 * Adiciona uma {@link Custa} para o {@link Cliente} e a para o {@link Colaborador} em um {@link Lancamento}.
	 * 
	 * 
	 * @param custa - a ser adicionada.
	 * @param cliente - a ser adicionado.
	 * @param colaborador - a ser adicionado.
	 * @param data - data do lancamento.
	 * @return {@link Custa}
	 * @throws IllegalArgumentException
	 */
	Custa adicionarCusta(Custa custa, Cliente cliente, Colaborador colaborador, Date data);
	
	/**
	 * Fecha o(s) {@link Lancamento}(s) que estao na sessao do servico.
	 */
	void fecharLacamento();	

	
	/**
	 * remove a custa do lancamento.
	 * 
	 * @param custa - a ser removida.
	 */
	void removerCusta(Custa custa);
	


	
}
