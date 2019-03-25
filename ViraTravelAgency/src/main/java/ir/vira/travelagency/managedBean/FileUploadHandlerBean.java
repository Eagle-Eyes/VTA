package ir.vira.travelagency.managedBean;

import ir.vira.travelagency.model.entity.Document;
import ir.vira.travelagency.model.repository.DocumentRepository;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import java.io.ByteArrayInputStream;

@ApplicationScoped
@Transactional
@ManagedBean
@Setter
@Getter
public class FileUploadHandlerBean extends BaseBean {
    
    protected static final Logger logger = LoggerFactory.getLogger(FileUploadHandlerBean.class);
    
    private UploadedFile uploadedFile;
    
    @Autowired
    private DocumentRepository documentRepository;
    
    public StreamedContent streamImage(byte[] file, String id) {
        logger.warn(String.format("image id:%s", id));
        
        FacesContext context = FacesContext.getCurrentInstance();
        
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            logger.warn("RENDER_RESPONSE");
            return new DefaultStreamedContent();
        } else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
//            id = context.getExternalContext().getRequestParameterMap().get(id);
            logger.warn("out of RENDER_RESPONSE");
            return new DefaultStreamedContent(new ByteArrayInputStream(file));
        }
    }
    
    public long uploadFile(FileUploadEvent event) {
        
        logger.warn(String.format("================================================================"));
        logger.warn(String.format("Uploaded File: %s", event.getFile().getFileName()));
        logger.warn(String.format("Uploaded File Size: %s", event.getFile().getSize()));
        logger.warn(String.format("Uploaded File Content Type: %s", event.getFile().getContentType()));
        
        UploadedFile uploadedFile = event.getFile();
        
        Document doc = new Document();
        doc.setDisplayName(uploadedFile.getFileName());
        doc.setType(uploadedFile.getContentType());
        doc.setData(uploadedFile.getContents());
        documentRepository.save(doc);
        
        logger.warn(String.format("uploadedFile '%s' uploaded successfully", event.getFile().getFileName()));
        logger.warn(String.format("================================================================"));
        
        return doc.getId();
    }
}
