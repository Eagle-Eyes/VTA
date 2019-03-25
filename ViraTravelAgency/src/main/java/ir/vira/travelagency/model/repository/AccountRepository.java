package ir.vira.travelagency.model.repository;

import ir.vira.travelagency.model.entity.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    
    boolean existsByAccountNameIgnoreCase(String name);
    
    boolean existsByEmailIgnoreCase(String email);
    
    int countByAccountName(String name);
    
    Account findByAccountName(String name);
    
    Account findByMobileNumber(String phoneNumber);
    
    Account findByEmailIgnoreCase(String phoneNumber);
    
    
}
