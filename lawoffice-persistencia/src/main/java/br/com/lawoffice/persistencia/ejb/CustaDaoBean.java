/**
 *
 */
package br.com.lawoffice.persistencia.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.lawoffice.persistencia.CustaDao;

/**
 * classe de implementacao para o {@link CustaDao} utilizando tecnologia EJB 3.1
 *
 * @author robson
 *
 */
@Stateless
@Local(CustaDao.class)
public class CustaDaoBean extends BaseDaoBean implements CustaDao {

}
