/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Venue;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Database.MySqlConnection;

/**
 *
 * @author admin
 */
public class VenueDao {
    MySqlConnection mysql = new MySqlConnection();   
    public List<Venue> getVenuesByType(String type) {
    List<Venue> venues = new ArrayList<>();
    Connection conn = mysql.openConnection(); 
    String sql = "SELECT * FROM venue_details WHERE venue_type = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {   
            pstmt.setString(1, type);
            ResultSet rs = pstmt.executeQuery();        
            while (rs.next()) {            
                Venue venue = new Venue(
                         rs.getString("venue_name"),                 // int venuePrice
                         rs.getString("image"),              // String venueName
                         rs.getString("venue_status"),          // String venueLocation
                         rs.getString("location"),            // String venueStatus
                         rs.getInt("price"), 
                         rs.getInt("venue_id")              // String venueImage
                );
                venues.add(venue);        
            }    
        } catch (SQLException ex) {        
            ex.printStackTrace();    
        } 
        finally {        
            mysql.closeConnection(conn);    
        }    
        return venues;
}
}
