/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.tul.data;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "jizda")
public class Ride {

    @Id
    @OneToMany
    @JoinColumn(name = "linka")
    private Trace trace;

    @OneToMany
    @JoinColumn(name = "cislo_rp")
    private Driver driver;

    @OneToMany
    @JoinColumn(name = "spz")
    private Bus bus;

    @Id
    @Column(name="cas")
    private Timestamp time;

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
    
    
}
