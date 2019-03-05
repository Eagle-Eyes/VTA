package ir.vira.travelagency.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class AirPlaneSit extends BaseEntity {

    private String sitRow;
    private String sitColumn;

    @ManyToOne
    private AirPlane airPlane;

    @OneToMany(mappedBy = "airPlaneSit")
    private Set<Ticket> tickets;

}
