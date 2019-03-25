package ir.vira.travelagency.model.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String displayName;
    @CreationTimestamp
    protected Date registeredDate;
    @UpdateTimestamp
    protected Date updatedDate;
    @Temporal(TemporalType.TIMESTAMP)
    protected Date deleteDate;
    
    
    public BaseEntity() {
    }
    
    public BaseEntity(String displayName) {
        this.displayName = displayName;
    }
    
    @Override
    public boolean equals(Object obj) {
        
        if (obj != null
                    && this.getClass() == obj.getClass()
                    && this.getId() == ((BaseEntity) obj).getId()
        ) {
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        return (int) ((17 * 31) + id);
    }
}
