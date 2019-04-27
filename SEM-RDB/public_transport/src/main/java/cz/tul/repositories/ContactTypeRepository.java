package cz.tul.repositories;

import cz.tul.data.ContactType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactTypeRepository extends CrudRepository<ContactType, String> {

}