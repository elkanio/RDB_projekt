/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.tul.fm.public_transportation_db_objects;

import java.sql.Timestamp;

/**
 *
 * @author FilipKrat
 */
public class Ride {
    private Trace trace;
    private Driver driver;
    private Bus bus;
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
