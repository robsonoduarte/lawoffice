/**
 * 
 */
package br.com.lawoffice.web.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.lawoffice.dominio.Colaborador;

/**
 * Validator para o {@link Colaborador} utilizado nas interfaces do sistema.
 * 
 * 
 * @author rduarte
 *
 */
@FacesValidator("colaboradorValidator")
public class ColaboradorValidator implements Validator {


	@Override
	public void validate(FacesContext facesContext, UIComponent uiComponent, Object colaborador) throws ValidatorException {
		if(colaborador == null){	
			throw new ValidatorException(
				 new FacesMessage(
					FacesMessage.SEVERITY_ERROR, 
					"Existem campos obrigatórios sem preenchimento..: ", 
					"O Colaborador é obrigatório"
				));
		}
	}
}
