/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.tul.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author FilipKrat
 */
@Entity
@Table(name = "typkontaktu")
public class ContactType {

    @Id
    @Column(name="typ")
    private String type;

    public ContactType(){}

    public ContactType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
    
}
