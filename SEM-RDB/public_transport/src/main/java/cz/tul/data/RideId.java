package cz.tul.data;

import java.io.Serializable;
import java.sql.Timestamp;

public class RideId implements Serializable {
    private Timestamp time;
    private Trace trace;

    public RideId(Trace trace, Timestamp time){
        this.trace = trace;
        this.time = time;
    }

}
