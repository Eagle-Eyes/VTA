package ir.vira.travelagency.managedBean.crud;

import ir.vira.travelagency.model.entity.Account;
import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@RequestScoped
@ManagedBean
@Setter
@Getter
public class AccountCrudBean extends BaseCrudBean<Account> {
    
    public AccountCrudBean() throws IllegalAccessException, InstantiationException {
        super(Account.class);
    }
}
