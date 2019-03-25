package ir.vira.travelagency.managedBean.crud;

import ir.vira.travelagency.managedBean.BaseBean;
import ir.vira.travelagency.model.entity.BaseEntity;
import ir.vira.travelagency.model.repository.CrudRepository;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Collections;
import java.util.List;

@Setter
@Getter
@Transactional
public abstract class BaseCrudBean<T extends BaseEntity> extends BaseBean {
    
    protected static final Logger logger = LoggerFactory.getLogger(BaseCrudBean.class);
    
    @Autowired
    private CrudRepository<T, Long> crudRepository;
    
    private List<T> items;
    private Class<T> clazz;
    private T item;
    
    public BaseCrudBean(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        this.clazz = clazz;
        logger.warn("Crud Bean");
        items = (List<T>) Collections.EMPTY_LIST;
        item = this.clazz.newInstance();
        list();
    }
    
    public void add() throws IllegalAccessException, InstantiationException {
        
        logger.warn("add");
        item = crudRepository.save(item);
        items.add(0, item);
        logger.warn(String.format("%s saved: ID: %s, DisplayName: %s", item.getClass().getSimpleName(), item.getId(), item.getDisplayName()));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, String.format("%s added", item.getClass().getSimpleName()), String.format("%s %s was added.", item.getClass().getSimpleName(), item.getDisplayName())));
        item = this.clazz.newInstance();
        
    }
    
    public void edit() throws IllegalAccessException, InstantiationException {
        
        logger.warn("edit");
        T item = crudRepository.save(this.item);
        items.remove(item);
        items.add(0, item);
        logger.warn(String.format("%s saved: ID: %s, DisplayName: %s", item.getClass().getSimpleName(), item.getId(), item.getDisplayName()));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, String.format("%s edited", item.getClass().getSimpleName()), String.format("%s %s was edited.", item.getClass().getSimpleName(), item.getDisplayName())));
        this.item = this.clazz.newInstance();
        
    }
    
    
    public void remove(Long id) {
        
        logger.warn("remove");
        T item = crudRepository.getById(id);
        crudRepository.delete(item);
        items.remove(item);
        logger.warn(String.format("%s removed: ID: %s, DisplayName: %s", item.getClass().getSimpleName(), item.getId(), item.getDisplayName()));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, String.format("%s removed", item.getClass().getSimpleName()), String.format("%s %s was removed.", item.getClass().getSimpleName(), item.getDisplayName())));
        
    }
    
    public void load(Long id) {
        
        logger.warn("load");
        item = crudRepository.getById(id);
        logger.warn(String.format("%s loaded: ID: %s, DisplayName: %s", item.getClass().getSimpleName(), item.getId(), item.getDisplayName()));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, String.format("%s editing", item.getClass().getSimpleName()), String.format("%s %s is ready for editing ...", item.getClass().getSimpleName(), item.getDisplayName())));
    }
    
    public void list() {
        logger.warn("list");
        items = crudRepository.findAll();
    }
}
