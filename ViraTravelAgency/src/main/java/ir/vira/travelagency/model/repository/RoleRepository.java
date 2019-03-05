package ir.vira.travelagency.model.repository;

import ir.vira.travelagency.model.entity.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {


}
