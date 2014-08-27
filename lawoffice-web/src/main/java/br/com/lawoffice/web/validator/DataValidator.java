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

/**
 * Validator para a natureza do lançamento de custas obtidos na view.  
 *
 * @author rduarte
 *
 */
@FacesValidator("dataValidator")
public class DataValidator implements Validator {


	@Override
	public void validate(FacesContext facesContext, UIComponent uiComponent, Object object) throws ValidatorException {

		if(object == null){
			throw new ValidatorException(
				new FacesMessage(
					FacesMessage.SEVERITY_ERROR, 
					"Existem campos obrigatórios sem preenchimento..: ", 
					"A Data é obrigatória"
			));
		}
	}
}
