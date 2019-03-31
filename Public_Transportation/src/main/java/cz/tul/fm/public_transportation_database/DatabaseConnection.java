/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.tul.fm.public_transportation_database;

import java.sql.*;

/**
 *
 * @author FilipKrat
 */
public class DatabaseConnection {

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/dbname", "root", "root")) {
                //here dbname is database name, root is username and password

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void query(String query, Connection con) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        //while(rs.next())
        //System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)); 
    }
}
