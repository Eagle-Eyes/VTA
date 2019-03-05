package ir.vira.travelagency.model.repository;

import ir.vira.travelagency.model.entity.NaturalPerson;
import org.springframework.stereotype.Repository;

@Repository
public interface NaturalPersonRepository extends CrudRepository<NaturalPerson, Long> {
}
