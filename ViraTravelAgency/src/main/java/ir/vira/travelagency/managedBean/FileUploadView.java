package ir.vira.travelagency.managedBean;

import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
@Getter
@Setter
public class FileUploadView extends BaseBean {
    
    protected static final Logger logger = LoggerFactory.getLogger(FileUploadView.class);
    
    private UploadedFile uploadedFile;
    
    public void upload() {
        logger.warn("A");
        
        if (uploadedFile != null) {
            logger.warn("B");
            
            FacesMessage message = new FacesMessage("Succesful", uploadedFile.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void handleFileUpload(FileUploadEvent event) {
        logger.warn("C");
        
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
