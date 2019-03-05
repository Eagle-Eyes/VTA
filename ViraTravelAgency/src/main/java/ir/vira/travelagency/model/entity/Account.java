package ir.vira.travelagency.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Account extends BaseEntity {

    @Column(unique = true)
    @NotNull(message = "Account Name can't be null!")
    private String accountName;

    @NotNull(message = "Email can't be null!")
    private String email;

    private String mobileNumber;

    @Size(min = 8, message = "At least should be 8 characters!")
    @NotNull(message = "Password can't be null!")
    private String password;

    @OneToOne
    private NaturalPerson naturalPerson;

    @ManyToMany
    private Set<Role> roles;

}
