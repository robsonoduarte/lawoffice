package br.com.lawoffice.caixa.extrato;

import java.util.Date;

import br.com.lawoffice.dominio.Cliente;
import br.com.lawoffice.dominio.Colaborador;


/**
 * Interface para o serviço de extrato do caixa do escritorio.
 * 
 * @author robson
 *
 */
public interface ExtratoService{
	
	
	/**
	 * Obtem o extrato para o {@link Colaborador} no periodo passado.  
	 * <br>
	 * Se o {@link Colaborador} passado nao estiver cadastrado null sera retornando.
	 * 
	 * 
	 * @param dataInicial - para consulta.
	 * @param dataFinal - para consulta.
	 * @param colaborador - da consulta.
	 * @return {@link ExtratoDTO} com o resultado da consulta.
	 */
	public ExtratoDTO getExtratoColaborador(Date dataInicial, Date dataFinal, Colaborador colaborador);
	
	
	/**
	 * Obtem o extrato para o {@link Cliente} no periodo passado. 
	 * <br>
	 * Se o {@link Cliente} passado nao estiver cadastrado null sera retornando.
	 * 
	 * @param dataInicial - para consulta.
	 * @param dataFinal - para consulta.
	 * @param cliente - da consulta.
	 * @return {@link ExtratoDTO} com o resultado da consulta.
	 */
	public ExtratoDTO getExtratoCliente(Date dataInicial, Date dataFinal, Cliente cliente);
	
	
	
	/**
	 * Gera o extrato apartir do tipo passado.
	 * 
	 * @param tipoExtrato a ser gerado.
	 * @return extrato em array de byte.
	 */
	public byte[] gerarExtrato(TipoExtrato tipoExtrato); 
}
