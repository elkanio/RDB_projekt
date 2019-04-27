package cz.tul.repositories;

import cz.tul.data.Locality;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalityRepository extends CrudRepository<Locality, String> {

}