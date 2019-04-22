package cz.tul.fm.public_transportation_db_objects;

import javax.persistence.*;

/**
 *
 * @author FilipKrat
 */
@Entity
@Table(name = "autobus")
public class Bus {
    @OneToOne
    @JoinColumn(name = "znacka")
    private Brand brand;

    @Id
    @Column(name="spz")
    private String plate;

    public Bus(Brand brand, String plate) {
        this.brand = brand;
        this.plate = plate;
    }

    public Brand getBrand() {
        return brand;
    }

    public String getPlate() {
        return plate;
    }
    
    
}
