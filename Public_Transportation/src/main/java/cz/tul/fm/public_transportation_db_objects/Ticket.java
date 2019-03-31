/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.tul.fm.public_transportation_db_objects;

/**
 *
 * @author FilipKrat
 */
public class Ticket {
    private int ticketNo;
    private Trace trace;
    private Client client;

    public Ticket(int ticketNo, Trace trace) {
        this.ticketNo = ticketNo;
        this.trace = trace;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getTicketNo() {
        return ticketNo;
    }

    public Trace getTrace() {
        return trace;
    }

    public Client getClient() {
        return client;
    }

    
    
    
}
