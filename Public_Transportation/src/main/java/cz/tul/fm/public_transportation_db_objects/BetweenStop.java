package cz.tul.fm.public_transportation_db_objects;

import javax.persistence.*;

@Entity
@Table(name = "mezizastavka")
public class BetweenStop {
    @Id
    @Column(name="nazev")
    private String name;

    @Id
    @Column(name="linka")
    private String linka;
}
