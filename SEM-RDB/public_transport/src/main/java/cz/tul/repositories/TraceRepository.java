package cz.tul.repositories;

import cz.tul.data.Country;
import cz.tul.data.Trace;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraceRepository extends CrudRepository<Trace, String> {

}