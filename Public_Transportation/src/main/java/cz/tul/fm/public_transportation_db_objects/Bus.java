package cz.tul.fm.public_transportation_db_objects;

/**
 *
 * @author FilipKrat
 */
public class Bus {
    private String brand;
    private String plate;

    public Bus(String brand, String plate) {
        this.brand = brand;
        this.plate = plate;
    }

    public String getBrand() {
        return brand;
    }

    public String getPlate() {
        return plate;
    }
    
    
}
