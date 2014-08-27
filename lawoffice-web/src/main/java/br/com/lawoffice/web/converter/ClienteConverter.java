/**
 *
 */
package br.com.lawoffice.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.lang.math.NumberUtils;

import br.com.lawoffice.dados.PessoaServiceLocal;
import br.com.lawoffice.dominio.Cliente;

/**
 *
 * Faces Converter para o {@link Cliente}
 *
 * @author robson
 *
 */
@FacesConverter("clienteConverter")
public class ClienteConverter implements Converter {



	private PessoaServiceLocal pessoaServiceLocal;


	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String clienteID) {

		try {
			Context context = new InitialContext();
			pessoaServiceLocal = (PessoaServiceLocal) context.lookup("java:global/lawoffice-ear-1.0.0/lawoffice-dados-1.0.0/PessoaServiceBean!br.com.lawoffice.dados.PessoaServiceLocal");
		} catch (NamingException e) {
			new RuntimeException(e);
		}

		if(NumberUtils.isNumber(clienteID)){
			Cliente cliente = new Cliente();
			cliente.setId(Long.valueOf(clienteID));
			return pessoaServiceLocal.localizar(Cliente.class, cliente);
		}
		else
			return null;
	}


	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object cliente) {
		return ((Cliente) cliente).getId().toString();
	}

}
