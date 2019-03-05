package ir.vira.travelagency.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;


@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Ticket extends BaseEntity {

    @ManyToOne
    private Trip trip;

    @ManyToOne
    private NaturalPerson passenger;

    @ManyToOne
    private AirPlaneSit airPlaneSit;

}
