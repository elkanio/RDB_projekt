/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.tul.data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


@Entity
@Table(name = "jizda")
@IdClass(RideId.class)
public class Ride implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "linka")
    private Trace trace;

    @ManyToOne
    @JoinColumn(name = "cislo_rp")
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "spz")
    private Bus bus;

    @Id
    @Column(name="cas")
    private Timestamp time;

    public Ride(){}

    public Ride(Trace trace, Driver driver, Bus bus, Timestamp time) {
        this.trace = trace;
        this.driver = driver;
        this.bus = bus;
        this.time = time;
    }

    public Trace getTrace() {
        return trace;
    }

    public Driver getDriver() {
        return driver;
    }

    public Bus getBus() {
        return bus;
    }

    public Timestamp getTime() {
        return time;
    }
    
    @Override
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ride other = (Ride) obj;
        if (trace == null) {
            if (other.trace != null)
                return false;
        } else if (!trace.equals(other.trace))
            return false;
        if (time == null) {
            if (other.time != null)
                return false;
        } else if (!time.equals(other.time))
            return false;
        return true;

    }
}
