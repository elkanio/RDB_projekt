/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.tul.fm.public_transportation_db_objects;

import java.util.ArrayList;

/**
 *
 * @author FilipKrat
 */
public class Trace {
    private String traceNo;
    private Locality from;
    private Locality to;
    private ArrayList<Locality> stations;

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
