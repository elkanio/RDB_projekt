/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.tul.data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author FilipKrat
 */
@Entity
@Table(name = "trasy")
public class Trace {
    @Id
    @Column(name="linka")
    private String traceNo;

    @ManyToMany
    @JoinColumn(name = "odkud")
    private Locality from;

    @ManyToMany
    @JoinColumn(name = "kam")
    private Locality to;

    private ArrayList<Locality> stations;
    private ArrayList<Timestamp> stationsTime;

    public Trace(String traceNo, Locality from, Locality to) {
        this.traceNo = traceNo;
        this.from = from;
        this.to = to;
    }

    public void setStations(ArrayList<Locality> stations) {
        this.stations = stations;
    }

    public String getTraceNo() {
        return traceNo;
    }

    public Locality getFrom() {
        return from;
    }

    public Locality getTo() {
        return to;
    }

    public ArrayList<Locality> getStations() {
        return stations;
    }
    
    
}
