package ir.vira.travelagency.model.repository;

import ir.vira.travelagency.model.entity.Location;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {
}
