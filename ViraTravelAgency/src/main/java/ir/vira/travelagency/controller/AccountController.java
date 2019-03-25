package ir.vira.travelagency.controller;

import ir.vira.travelagency.model.entity.Account;
import ir.vira.travelagency.model.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    
    @Autowired
    private AccountRepository accountRepository;
    
    @GetMapping({"/account", "/account/list"})
    public String list() {
        logger.warn("AccountController");
        
        return "/jsf/account_list.xhtml";
    }
    
    @GetMapping({"/resetPassword/{accountName}"})
    public String restPassword(@PathVariable String accountName, @RequestParam String newPassword) {
        
        Account acc = accountRepository.findByAccountName(accountName);
        acc.setPassword(newPassword);
        accountRepository.save(acc);
        
        return "/jsf/account_list.xhtml";
    }
    
    
}
