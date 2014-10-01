/**
 *
 */
package br.com.lawoffice.caixa.extrato;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateful;

import br.com.lawoffice.caixa.extrato.jasper.SimpleFactoryExtratoReportJasper;
import br.com.lawoffice.dominio.Cliente;
import br.com.lawoffice.dominio.Colaborador;
import br.com.lawoffice.dominio.Conta;
import br.com.lawoffice.dominio.Custa;
import br.com.lawoffice.dominio.HistoricoConta;
import br.com.lawoffice.dominio.Lancamento;
import br.com.lawoffice.dominio.Pessoa;
import br.com.lawoffice.persistencia.ClienteDao;
import br.com.lawoffice.persistencia.ColaboradorDao;
import br.com.lawoffice.persistencia.HistoricoContaDao;
import br.com.lawoffice.persistencia.LancamentoDao;

/**
 *
 * Implementação para o serviço de Extrato.
 *
 *
 * @author robson
 * @see ExtratoService
 *
 */
@Stateful
@Local(ExtratoServiceLocal.class)
@Remote(ExtratoServiceRemote.class)
public class ExtratoServiceBean implements ExtratoService{


	@EJB
	private ColaboradorDao colaboradorDao;


	@EJB
	private ClienteDao clienteDao;


	@EJB
	private HistoricoContaDao historicoContaDao;


	@EJB
	private LancamentoDao lancamentoDao;


	private FactoryExtratoReport factoryExtratoReport;


	private ExtratoDTO extratoDTO;



	@Override
	public ExtratoDTO getExtratoColaborador(Date dataInicial, Date dataFinal,
			Colaborador colaborador) {
		validarParametros(dataInicial, dataFinal, colaborador);

		colaborador =
				colaboradorDao.localizar(Colaborador.class, colaborador);

		if(colaborador == null)
			return null;


		createExtratoDTO(
			dataInicial,
			dataFinal,
			colaborador,
			colaborador.getConta()
		);

		addItensExtrato(
			lancamentoDao.getLancamentos(
					dataInicial,
					dataFinal,
					colaborador
				)
			);

		return extratoDTO;
	}



	@Override
	public ExtratoDTO getExtratoCliente(Date dataInicial, Date dataFinal,
			Cliente cliente) {
		validarParametros(dataInicial, dataFinal, cliente);

		cliente =
				clienteDao.localizar(Cliente.class, cliente);

		if(cliente == null)
			return null;

		createExtratoDTO(
				dataInicial,
				dataFinal,
				cliente,
				cliente.getConta()
			);


		addItensExtrato(
			lancamentoDao.getLancamentos(
					dataInicial,
					dataFinal,
					cliente
				)
			);


		return extratoDTO;
	}




	/**
	 *
	 * Adiciona os {@link ItemExtrato} ao {@link ExtratoDTO} apartir do(s) {@link HistoricoConta} e do(s) {@link Lancamento}
	 * obtidos no periodo da consulta.
	 *
	 * @param historicosConta - obtidos no periodo da consulta.
	 * @param lancamentos - obtidos no periodo da consulta.
	 */
	private void addItensExtrato(List<Lancamento> lancamentos) {

		for (Lancamento lancamento : lancamentos) {
			for (Custa custa : lancamento.getCustas()) {
				extratoDTO.addItemExtrato(
					new ItemExtrato(
						lancamento.getDataLancamento(),
						custa.getNatureza(),
						custa.getValor()
					)
				);
			}
		}

		Collections.sort(extratoDTO.getItensExtrato());
	}


	/**
	 * Cria um {@link ExtratoDTO}
	 *
	 * @param dataInicial - da consulta.
	 * @param dataFinal - da consulta.
	 * @param pessoa - da consulta.
	 * @param conta - da pessoa da consulta.
	 * @return  {@link ExtratoDTO}
	 */
	private void createExtratoDTO(Date dataInicial, Date dataFinal, Pessoa pessoa, Conta conta) {
		extratoDTO =
			new ExtratoDTO(
				pessoa.getNome(),
				getSaldoAnterior(conta,dataInicial),
				conta.getSaldo(),
				dataInicial,
				dataFinal
			);
	}




	/**
	 * Obtem o saldo anterior a data inicial de pesquisa para conta passada.
	 *
	 *
	 * @param conta - a obter o saldo anterior.
	 * @param dataInicial - de consulta.
	 * @return - {@link BigDecimal} com saldo anterior
	 */
	private BigDecimal getSaldoAnterior(Conta conta, Date dataInicial) {

		// data anterior / - 1 dia
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dataInicial);
		calendar.add(Calendar.DAY_OF_MONTH, -1);

		List<HistoricoConta> historicosConta =
				historicoContaDao.getHistoricosConta(calendar.getTime(), conta);

		if(historicosConta.isEmpty())
			return new BigDecimal(0.0);


		return historicosConta.get(historicosConta.size() -1).getSaloAnterior();
	}




	@Override
	public byte[] gerarExtrato(TipoExtrato tipoExtrato) {
		if(extratoDTO == null)
			throw new IllegalStateException("Nao foi realizado nenhuma pesquisa");

		if(factoryExtratoReport == null){
			factoryExtratoReport  = new SimpleFactoryExtratoReportJasper();
		}

		ExtratoReport extratoReport =
				factoryExtratoReport.createExtratoReport(tipoExtrato);

		try {
			return extratoReport.gerarExtrato(extratoDTO);
		} catch (Exception e) {
			return null;
		}
	}




	/**
	 * valida os paramentros básicos de entrada do serviço.
	 *
	 * @param dataInicial - a ser avalida.
	 * @param dataFinal - a ser avalida.
	 * @param pessoa - a ser avalida.
	 */
	private void validarParametros(Date dataInicial, Date dataFinal,Pessoa pessoa) {
		if(dataInicial == null)
			throw new IllegalArgumentException("Data inicial nula");
		if(dataFinal == null)
			throw new IllegalArgumentException("Data final nula");
		if(pessoa == null || pessoa.getId() == null)
			throw new IllegalArgumentException("Pessoa nula ou Pessoa sem ID");
	}




	public void setColaboradorDao(ColaboradorDao colaboradorDao) {
		this.colaboradorDao = colaboradorDao;
	}


	public void setClienteDao(ClienteDao clienteDao) {
		this.clienteDao = clienteDao;
	}


	public void setHistoricoContaDao(HistoricoContaDao historicoContaDao) {
		this.historicoContaDao = historicoContaDao;
	}


	public void setLancamentoDao(LancamentoDao lancamentoDao) {
		this.lancamentoDao = lancamentoDao;
	}



	public void setFactoryExtratoReport(FactoryExtratoReport factoryExtratoReport) {
		this.factoryExtratoReport = factoryExtratoReport;
	}
}
