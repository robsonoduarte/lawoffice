/**
 * 
 */
package br.com.lawoffice.persistencia.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.lawoffice.persistencia.ColaboradorDao;

/**
 * classe de implementacao para o {@link ColaboradorDao} utilizando tecnologia EJB 3.1
 * 
 * @author robson
 *
 */
@Stateless
@Local(ColaboradorDao.class)
public class ColaboradorDaoBean extends BaseDaoBean implements ColaboradorDao {
	
}
