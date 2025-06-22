package Dao;

import Model.Venue;
import Model.Userdata;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Database.MySqlConnection;

public class VenueDao {
    MySqlConnection mysql = new MySqlConnection();   

    public Userdata getUserById(int userId) {
        Userdata user = null;
        String query = "SELECT * FROM users WHERE id = ?";
        Connection conn = mysql.openConnection();

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String f_name = rs.getString("f_name");
                String l_name = rs.getString("l_name");
                long ph_number = rs.getLong("ph_number");
                String role = rs.getString("role");
                String password = rs.getString("password");
                
                user = new Userdata(f_name, l_name, ph_number, role, password);
                user.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysql.closeConnection(conn);
        }

        return user;
    }

    public Venue getVenueById(int userId) {
        Venue venue = null;
        String query = "SELECT * FROM Venue WHERE id = ?";
        Connection conn = mysql.openConnection();

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("venueName");
                String description = rs.getString("venueDescription");
                String location = rs.getString("venueLocation");
                int price = rs.getInt("venuePrice");
                String type = rs.getString("venueType");
                String image = rs.getString("venueImage");
                Long contact = rs.getLong("venueContact");

                venue = new Venue(userId, name, description, location, price, type, image, contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysql.closeConnection(conn);
        }

        return venue;
    }

    public List<Venue> getVenuesByType(String type) {
        List<Venue> venues = new ArrayList<>();
        String sql = "SELECT * FROM Venue WHERE venueType = ?";
        Connection conn = mysql.openConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {   
            pstmt.setString(1, type);
            ResultSet rs = pstmt.executeQuery();        

            while (rs.next()) {            
                int id = rs.getInt("id");
                String name = rs.getString("venueName");
                String description = rs.getString("venueDescription");
                String location = rs.getString("venueLocation");
                int price = rs.getInt("venuePrice");
                String venueType = rs.getString("venueType");
                String image = rs.getString("venueImage");
                Long contact = rs.getLong("venueContact");

                Venue venue = new Venue(id, name, description, location, price, venueType, image, contact);
                venues.add(venue);        
            }    
        } catch (SQLException ex) {        
            ex.printStackTrace();    
        } finally {        
            mysql.closeConnection(conn);    
        }    

        return venues;
    }
    
    public boolean updateVenue(Venue venue) {
        String sql = "UPDATE Venue SET venueName = ?, venueContact = ?, venueDescription = ?, "
                + "venueLocation = ?, venuePrice = ?, venueType = ?, venueImage = ? "
                + "WHERE id = ?";

        Connection conn = mysql.openConnection();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, venue.getVenueName());
            stmt.setString(2, String.valueOf(venue.getVenueContact())); // Cast Long to String (VARCHAR)
            stmt.setString(3, venue.getVenueDecription());
            stmt.setString(4, venue.getVenueLocation());
            stmt.setInt(5, venue.getVenuePrice());
            stmt.setString(6, venue.getVenueType());
            stmt.setString(7, venue.getVenueImage());
            stmt.setInt(8, venue.getId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysql.closeConnection(conn);
        }

        return false;
    }
    
    public ArrayList<String> getRulesByGame(String game) {
        ArrayList<String> rulesList = new ArrayList<>();
        String sql = "SELECT rule FROM rules WHERE game = ?";
        Connection conn = mysql.openConnection();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, game);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                rulesList.add(rs.getString("rule"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle logging or rethrow as needed
        }

        return rulesList;
    }

}
