package ir.vira.travelagency.model.repository;

import ir.vira.travelagency.model.entity.Document;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends CrudRepository<Document, Long> {

}
