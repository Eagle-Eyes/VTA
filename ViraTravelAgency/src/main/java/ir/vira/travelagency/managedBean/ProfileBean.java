package ir.vira.travelagency.managedBean;

import ir.vira.travelagency.model.entity.Account;
import ir.vira.travelagency.model.repository.AccountRepository;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean
@Getter
@Setter
public class ProfileBean extends BaseBean {
    
    private static final Logger logger = LoggerFactory.getLogger(ProfileBean.class);
    
    private Account account;
    
    @Autowired
    private AccountRepository accountRepository;
    
    public ProfileBean() {
        
        logger.warn("ProfileBean");
        init();
    }
    
    //    @Transactional
    public void init() {
        String loginID = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = accountRepository.findByAccountName(loginID);
    }
    
    
}
