package ir.vira.travelagency.model.repository;

import ir.vira.travelagency.model.entity.Ticket;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {
}
