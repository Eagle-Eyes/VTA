package ir.vira.travelagency.managedBean.crud;

import ir.vira.travelagency.model.entity.Role;
import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@RequestScoped
@ManagedBean
@Getter
@Setter
public class RoleCrudBean extends BaseCrudBean<Role> {
    
    public RoleCrudBean() throws IllegalAccessException, InstantiationException {
        super(Role.class);
    }
}
