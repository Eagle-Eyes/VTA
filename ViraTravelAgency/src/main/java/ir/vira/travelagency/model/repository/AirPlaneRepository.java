package ir.vira.travelagency.model.repository;

import ir.vira.travelagency.model.entity.AirPlane;
import org.springframework.stereotype.Repository;

@Repository
public interface AirPlaneRepository extends CrudRepository<AirPlane, Long> {
}
