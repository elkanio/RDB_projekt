package cz.tul.fm.public_transportation_db_objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "znacka")
public class Brand {
    @Id
    @Column(name="znacka")
    private String brand;

    public Brand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {return brand;}

}
