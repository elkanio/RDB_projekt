package cz.tul.data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "mezizastavka")
@IdClass(BetweenStopId.class)
public class BetweenStop implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name="nazev")
    private Locality name;

    @Id
    @ManyToOne
    @JoinColumn(name="linka")
    private Trace linka;

    public BetweenStop(){}
}
