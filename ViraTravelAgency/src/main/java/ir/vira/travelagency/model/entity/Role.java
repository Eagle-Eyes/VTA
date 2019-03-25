package ir.vira.travelagency.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import java.util.Set;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Role extends BaseEntity {
    
    @ManyToMany(mappedBy = "roles")
    private Set<Account> accounts;
    
    public Role(String displayName) {
        super(displayName);
    }
    
    public Role() {
    }
}
