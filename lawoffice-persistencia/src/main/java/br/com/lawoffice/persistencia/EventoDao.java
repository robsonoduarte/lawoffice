/**
 * 
 */
package br.com.lawoffice.persistencia;

import java.util.Date;
import java.util.List;

import br.com.lawoffice.dominio.Agenda;
import br.com.lawoffice.dominio.Colaborador;
import br.com.lawoffice.dominio.Evento;

/**
 * 
 * Interface de Acesso a Base de Dados para o {@link Evento} do {@link Colaborador}.
 * 
 * @author robson
 *
 */
public interface EventoDao extends BaseDao {

	/**
	 * 
	 * Lista os {@link Evento}  da {@link Agenda} do {@link Colaborador} entre as datas passadas.
	 * 
	 * @param colaborador - no qual a {@link Agenda} será consultada.
	 * @param dataInicial - data incial para a consulta.
 	 * @param dateFinal - data final para a consulta.
	 * @return {@link List} de {@link Evento} encontrado no periodo passado.
	 */
	List<Evento> getEventos(Colaborador colaborador , Date dataInicial, Date dataFinal);
}
