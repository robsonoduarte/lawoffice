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
import br.com.lawoffice.dominio.Colaborador;

/**
 * Faces Converter para o {@link Colaborador}
 *
 * @author robson
 *
 */
@FacesConverter("colaboradorConverter")
public class ColaboradorConverter implements Converter {


	private PessoaServiceLocal pessoaServiceLocal;


	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String colaboradorID) {

		try {
			Context context = new InitialContext();
			pessoaServiceLocal =
					(PessoaServiceLocal) context.lookup("java:global/lawoffice-ear-1.0.0/lawoffice-dados-1.0.0/PessoaServiceBean!br.com.lawoffice.dados.PessoaServiceLocal");
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}

		if(NumberUtils.isNumber(colaboradorID)){
			Colaborador colaborador = new Colaborador();
			colaborador.setId(Long.valueOf(colaboradorID));
			return pessoaServiceLocal.localizar(Colaborador.class, colaborador);
		}
		else
			return null;

	}


	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object colaborador) {
		return ((Colaborador) colaborador).getId().toString();
	}

}
