package ir.vira.travelagency.model.service;

import ir.vira.travelagency.model.entity.Account;
import ir.vira.travelagency.model.entity.Role;
import ir.vira.travelagency.model.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

@Service
@Transactional
public class AccountService implements UserDetailsService, CrudService<Account> {
    
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    
    private AccountRepository accountRepository;
    private Pattern emailPattern;
    private Pattern passwordPattern;
    private Pattern mobilePattern;
    
    public AccountService(AccountRepository accountRepository, Pattern emailPattern, Pattern passwordPattern, Pattern mobilePattern) {
        this.accountRepository = accountRepository;
        this.emailPattern = emailPattern;
        this.passwordPattern = passwordPattern;
        this.mobilePattern = mobilePattern;
    }
    
    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        
        Account account;
        if (emailPattern.matcher(loginId).matches()) {
            logger.warn("Check by email ...");
            account = accountRepository.findByEmailIgnoreCase(loginId);
            
        } else if (mobilePattern.matcher(loginId).matches()) {
            logger.warn("Check by mobile ...");
            account = accountRepository.findByMobileNumber(loginId);
            
        } else {
            logger.warn("Check by account name ...");
            account = accountRepository.findByAccountName(loginId);
        }
        
        if (account != null) {
            logger.warn("Account: " + account.getDisplayName());
            
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            
            for (Role role : account.getRoles()) {
                logger.warn("Account Role: " + role.getDisplayName());
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getDisplayName()));
            }
            
            UserDetails userDetails = new User(account.getAccountName(), account.getPassword(), grantedAuthorities);
            
            return userDetails;
        } else {
            logger.warn(String.format("Account '%s' not found!", loginId));
            throw new UsernameNotFoundException(String.format("Account '%s' not found!", loginId));
        }
    }
    
    @Override
    public List<Account> list() {
        return accountRepository.findAllByDeleteDateIsNullOrderByIdDesc();
    }
    
    @Override
    public Account add(Account account) {
        return accountRepository.save(account);
    }
    
    @Override
    public Account edit(Account account) {
        return accountRepository.save(account);
    }
    
    @Override
    public Account get(Long id) {
        
        return accountRepository.getById(id);
        
    }
    
    @Override
    public Account remove(Long id) {
        Account temp = accountRepository.getOne(id);
        temp.setDeleteDate(new Date());
        return accountRepository.save(temp);
    }
    
}