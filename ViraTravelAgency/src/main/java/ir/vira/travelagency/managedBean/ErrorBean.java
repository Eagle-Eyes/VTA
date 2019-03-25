package ir.vira.travelagency.managedBean;//package ir.vira.travelagency.managedBean;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

@ViewScoped
@ManagedBean
@Getter
@Setter
public class ErrorBean extends BaseBean {
    
    private static final Logger logger = LoggerFactory.getLogger(ErrorBean.class);
    
    public ErrorBean() {
        logger.warn("Error Bean");
        
    }
    
    public void init() {
        logger.warn("Error Bean init");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Contact admin."));
        
    }
    
}
