package ir.vira.travelagency.model.repository;

import ir.vira.travelagency.model.entity.Trip;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends CrudRepository<Trip, Long> {
}
