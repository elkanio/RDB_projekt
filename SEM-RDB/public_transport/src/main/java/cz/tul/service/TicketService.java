package cz.tul.service;


import cz.tul.data.Ticket;
import cz.tul.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public void create(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    public boolean exists(String username) {
        return ticketRepository.exists(username);
    }

    public List<Ticket> getAllTickets() {
        return StreamSupport.stream(ticketRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void deleteTickets() {
        ticketRepository.deleteAll();
    }
}
