package cz.tul.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
