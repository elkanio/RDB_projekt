package cz.tul.repositories;

import cz.tul.data.BetweenStop;
import cz.tul.data.BetweenStopId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BetweenStopRepository extends CrudRepository<BetweenStop, BetweenStopId> {

}