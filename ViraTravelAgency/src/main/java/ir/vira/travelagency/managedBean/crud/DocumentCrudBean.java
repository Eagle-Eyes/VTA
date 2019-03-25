package ir.vira.travelagency.managedBean.crud;

import ir.vira.travelagency.model.entity.Document;
import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@RequestScoped
@ManagedBean
@Getter
@Setter
public class DocumentCrudBean extends BaseCrudBean<Document> {
    
    public DocumentCrudBean() throws IllegalAccessException, InstantiationException {
        super(Document.class);
    }
}
