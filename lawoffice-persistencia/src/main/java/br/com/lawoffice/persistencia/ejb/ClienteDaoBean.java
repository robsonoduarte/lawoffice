package br.com.lawoffice.persistencia.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.lawoffice.persistencia.ClienteDao;

/**
 * classe de implementacao para o {@link ClienteDao} utilizando tecnologia EJB 3.1
 * 
 * @author robson
 *
 */
@Stateless
@Local(ClienteDao.class)
public class ClienteDaoBean extends BaseDaoBean implements ClienteDao {

}
