package test;

import lombok.*;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@ManagedBean
@ApplicationScoped
@Data
@FacesValidator("yValidator")
public class ValidateY implements Validator {
    private double fromVal = -5;
    private double toVal = 3;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        String tmp = (String) o;
        double y = Double.parseDouble(tmp);
        if (y < fromVal || y > toVal) {
            //FacesMessage msg = new FacesMessage(String.format("Y должен быть от %d до %d", (int) fromVal, (int) toVal));
            throw new ValidatorException(new FacesMessage());
        }
    }
}
