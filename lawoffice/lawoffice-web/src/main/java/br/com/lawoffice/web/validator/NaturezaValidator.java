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

import org.apache.commons.lang.StringUtils;

/**
 * Validator para a natureza do lançamento de custas obtidos na view.  
 *
 * @author rduarte
 *
 */
@FacesValidator("naturezaValidator")
public class NaturezaValidator implements Validator {


	@Override
	public void validate(FacesContext facesContext, UIComponent uiComponent, Object object) throws ValidatorException {

		if(StringUtils.isBlank((String) object)){
			throw new ValidatorException(
				new FacesMessage(
					FacesMessage.SEVERITY_ERROR, 
					"Existem campos obrigatórios sem preenchimento..: ", 
					"A Natureza é obrigatória"
			));
		}
	}
}
