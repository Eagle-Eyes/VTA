package ir.vira.travelagency.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class AirPlane extends BaseEntity {
    
    @OneToMany(mappedBy = "airPlane")
    private Set<AirPlaneSit> sits;
    
    @ManyToMany
    private List<Document> images;
    
}
