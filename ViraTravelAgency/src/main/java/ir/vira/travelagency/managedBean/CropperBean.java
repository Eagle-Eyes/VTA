package ir.vira.travelagency.managedBean;

import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.CroppedImage;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import java.io.File;

@ManagedBean
@Getter
@Setter
public class CropperBean extends BaseBean {
    
    private CroppedImage croppedImage;
    
    private String newImageName;
    
    public CroppedImage getCroppedImage() {
        return croppedImage;
    }
    
    public void setCroppedImage(CroppedImage croppedImage) {
        this.croppedImage = croppedImage;
    }
    
    public void crop() {
        if (croppedImage == null) {
            return;
        }
        
        setNewImageName(getRandomImageName());
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String newFileName = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "demo" +
                                     File.separator + "images" + File.separator + "crop" + File.separator + getNewImageName() + ".jpg";
        
        FileImageOutputStream imageOutput;
        try {
            imageOutput = new FileImageOutputStream(new File(newFileName));
            imageOutput.write(croppedImage.getBytes(), 0, croppedImage.getBytes().length);
            imageOutput.close();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Cropping failed."));
            return;
        }
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Cropping finished."));
    }
    
    private String getRandomImageName() {
        int i = (int) (Math.random() * 100000);
        
        return String.valueOf(i);
    }
}
