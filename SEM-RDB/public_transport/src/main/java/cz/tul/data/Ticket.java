/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.tul.data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 *
 * @author FilipKrat
 */
@Entity
@Table(name = "jizdenka")
public class Ticket {

    @ManyToOne
    @JoinColumn(name = "linka")
    private Trace trace;

    @ManyToOne
    @JoinColumn(name = "email")
    private Client client;

    @JoinColumn(name="cas")
    private Timestamp time;

    @Id
    @Column(name = "cislo")
    private String ticketNo;

    public Ticket(){}

    public Ticket(Trace trace, Client client, Timestamp time, String ticketNo) {
        this.trace = trace;
        this.client = client;
        this.time = time;
        this.ticketNo = ticketNo;
    }

    public Trace getTrace() {return trace;}

    public Client getClient() {return client;}

    public Timestamp getTime() {return time;}

    public String getTicketNo() {return ticketNo;}

}
