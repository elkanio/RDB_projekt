/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.tul.fm.public_transportation_database;

import java.sql.*;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

public class DatabaseConnection {

    public void connect() {
       
    }

    
    public void hibernateDriverSetting(Driver d){
        Configuration con = new Configuration().configure().addAnnotatedClass(Driver.class);
        ServiceRegistry reg= new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
        Transaction ts = session.beginTransaction();
        session.save(d);
        ts.commit();
    }
    
     public void hibernateDriverFetching(){
        List<Driver> drivers = null;
        Configuration con = new Configuration().configure().addAnnotatedClass(Driver.class);
        ServiceRegistry reg= new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
        Transaction ts = session.beginTransaction();
        Query q = session.createQuery("From Driver");
        drivers = q.list();
        Driver d = (Driver)session.get(Driver.class,1);
        ts.commit();
    }
}
