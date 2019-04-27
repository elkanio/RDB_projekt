package cz.tul.repositories;

import cz.tul.data.Country;
import cz.tul.data.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, String> {

}