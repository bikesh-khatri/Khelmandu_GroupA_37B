/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Database.MySqlConnection;
import java.sql.*;
import Model.Booking;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class BookingDao {

    MySqlConnection mysql = new MySqlConnection();

    public boolean insertBooking(Booking booking) {
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
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }

    public boolean isAvailable(Booking booking) {
        Connection conn = mysql.openConnection();
        String sql = "SELECT COUNT(*) FROM bookings WHERE venue_id = ? AND booking_date = ? AND time_slot = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, booking.getVenue_id());
            stmt.setDate(2, booking.getBookingDate());
            stmt.setString(3, booking.getTimeSlot());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count == 0;
            }
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }

    public ArrayList<String[]> getAllBookingsForDashboard(int venueId) {
        ArrayList<String[]> bookingsList = new ArrayList<>();
        Connection conn = mysql.openConnection();

        String sql = "SELECT CONCAT(u.f_name, ' ', u.l_name) AS booked_by, "
                + "u.ph_number, b.booking_date, b.time_slot "
                + "FROM bookings b "
                + "JOIN users u ON b.user_id = u.id "
                + "WHERE b.venue_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, venueId);  // Set the venue_id filter
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String bookedBy = rs.getString("booked_by");
                String phone = rs.getString("ph_number");
                String bookingDate = rs.getString("booking_date");
                String timeSlot = rs.getString("time_slot");
                bookingsList.add(new String[]{bookedBy, phone, bookingDate, timeSlot});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            mysql.closeConnection(conn);
        }

        return bookingsList;
    }

}
