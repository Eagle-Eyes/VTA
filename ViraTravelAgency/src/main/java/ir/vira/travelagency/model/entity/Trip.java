package ir.vira.travelagency.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Trip extends BaseEntity {
    
    private Date tripDateTime;
    
    @ManyToOne
    private Location sourceLocation;
    
    @ManyToOne
    private Location destinationLocation;
    
    @ManyToOne
    private AirPlane airPlane;
    
    @OneToMany(mappedBy = "trip")
    private Set<Ticket> tickets;
    
}
