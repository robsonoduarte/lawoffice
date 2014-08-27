/**
 * 
 */
package br.com.lawoffice.web.validator;

import java.math.BigDecimal;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Validator para os valores monetários utilizados nas interfaces do sistema.  
 *
 * @author rduarte
 *
 */
@FacesValidator("valorValidator")
public class ValorValidator implements Validator {


	@Override
	public void validate(FacesContext facesContext, UIComponent uiComponent, Object object) throws ValidatorException {
		
		BigDecimal valor = (BigDecimal) object;
		
		if(valor == null || valor.doubleValue() <= 0){
			throw new ValidatorException(
				new FacesMessage(
					FacesMessage.SEVERITY_ERROR, 
					"Existem campos obrigatórios sem preenchimento..: ", 
					"O Valor é obrigatório e deve ser maior que zero"
			));
		}
	}
}
