/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import Database.MySqlConnection;
import java.sql.*;
import Model.Booking;
/**
 *
 * @author admin
 */
public class BookingDao {
    

    MySqlConnection mysql = new MySqlConnection();
    public boolean insertBooking(Booking booking){
        Connection conn = mysql.openConnection(); 
        String sql = "INSERT INTO bookings (card_id, booking_date, time_slot, payment_type) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, booking.getVenue_id());
            stmt.setDate(2, Date.valueOf(booking.getBookingDate()));
            stmt.setString(3, booking.getTimeSlot());
            stmt.setString(4, booking.getPaymentType());
            return stmt.executeUpdate() > 0;
}
        catch(SQLException ex){
            ex.printStackTrace();
        }
        finally{
            mysql.closeConnection(conn);
        }
        return false;
        
}    
}
