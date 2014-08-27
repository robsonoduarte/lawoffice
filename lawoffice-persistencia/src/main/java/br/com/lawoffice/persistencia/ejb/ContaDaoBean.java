/**
 * 
 */
package br.com.lawoffice.persistencia.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.lawoffice.persistencia.ContaDao;

/**
 * classe de implementacao para o {@link ContaDao} utilizando tecnologia EJB 3.1
 * 
 * @author robson
 *
 */
@Stateless
@Local(ContaDao.class)
public class ContaDaoBean extends BaseDaoBean implements ContaDao {

}
