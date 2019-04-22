/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.tul.fm.public_transportation_db_objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author FilipKrat
 */

@Entity
@Table(name = "lokalita")
public class Locality {
    @Id
    @Column(name="nazev")
    private String localityName;

    public Locality(String localityName) {
        this.localityName = localityName;
    }

    public String getLocalityName() {
        return localityName;
    }
    
}
