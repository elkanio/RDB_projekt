package cz.tul.data;

import javax.persistence.*;

@Entity
@Table(name = "autobus")
public class Bus {
    @ManyToOne
    @JoinColumn(name = "znacka")
    private Brand brand;

    @Id
    @Column(name="spz")
    private String plate;

    public Bus(){}

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
