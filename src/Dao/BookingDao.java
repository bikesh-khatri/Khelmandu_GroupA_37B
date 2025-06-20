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
        String sql = "INSERT INTO bookings (venue_id, booking_date, time_slot, payment_type, user_id) VALUES (?, ?, ?, ?, ?)";
        System.out.println("inserting");
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, booking.getVenue_id());
            stmt.setDate(2, booking.getBookingDate());
            stmt.setString(3, booking.getTimeSlot());
            stmt.setString(4, booking.getPaymentType());
            stmt.setInt(5, booking.getUser_id());
                 int rows = stmt.executeUpdate();
            return rows > 0;
        }
        catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
        finally{
            mysql.closeConnection(conn);
        }
        
        
}

    public boolean changeStatus(Booking booking){
        Connection conn = mysql.openConnection();
        String sql = "UPDATE venue_details SET venue_status = 'booked' WHERE venue_id = ? ";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1,booking.getVenue_id());
            int rows = stmt.executeUpdate();
            return rows > 0;
        }
        catch(SQLException ex){
            System.out.println(ex);
            return false;
        }
        finally{
            mysql.closeConnection(conn);
        }        
    }
}
