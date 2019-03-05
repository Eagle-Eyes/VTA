package ir.vira.travelagency.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;


@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class NaturalPerson extends BaseEntity {

    private String firstName;
    private String lastName;

    @Size(min = 10, message = "Length is not correct!")
    private String mobileNumber;
    private Date birthDate;

    @OneToOne(mappedBy = "naturalPerson")
    private Account account;

    @OneToMany(mappedBy = "passenger")
    private Set<Ticket> trips;
}
