package Dao;

import java.sql.*;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.Userdata;
import Database.MySqlConnection;
import javax.swing.JOptionPane;

/**
 * @author User
 */
public class Dao {
    MySqlConnection mysql = new MySqlConnection();

    public boolean signup(Userdata user) {
        Connection conn = mysql.openConnection();
        String sql = "INSERT INTO users(f_name, l_name, ph_number, role, password) VALUES (?, ?, ?, ?, ?)";
        String venueSql = "INSERT INTO Venue(id) VALUES (?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement venueStmt = conn.prepareStatement(venueSql)) {
            // Insert into users
            pstmt.setString(1, user.getF_name());
            pstmt.setString(2, user.getL_name());
            pstmt.setLong(3, user.getPh_number());
            pstmt.setString(4, user.getRole());
            pstmt.setString(5, user.getPassword());

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                // Get the generated user ID
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int userId = rs.getInt(1);

                    // Insert minimal row in Venue table
                    venueStmt.setInt(1, userId);
                    venueStmt.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Registration Successful");
                    return true;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Registration Failed");
            }

        } catch (SQLException ex) {
            Logger.getLogger(VenueDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mysql.closeConnection(conn);
        }

        return false;
    }

    public String checkuser(Long phno) {
        Connection conn = mysql.openConnection();
        
        String sql = "SELECT * FROM users where ph_number = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, phno);
            ResultSet result = pstmt.executeQuery();
            if(result.next()){
                return result.getString("role");
            }else{
                return "null";
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mysql.closeConnection(conn);
        }
        return "null";
    }
    
    public int logIn(String phno, String password) {
        MySqlConnection mySql = new MySqlConnection();
        Connection conn1 = mySql.openConnection();
        String sql = "SELECT * FROM users WHERE ph_number = ? AND password = ?";

        try (PreparedStatement pstm = conn1.prepareStatement(sql)) {
            pstm.setString(1, phno);
            pstm.setString(2, password);
            ResultSet rs = pstm.executeQuery(); 

            if (rs.next()) {
                
                JOptionPane.showMessageDialog(null, "Login successful");
                return rs.getInt("id");
            } else {
                // Login failed
                JOptionPane.showMessageDialog(null, "Login failed: Invalid username or password");
                return 0;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "An error occurred during login");
        } finally {
            mySql.closeConnection(conn1);
        }
        return 0;
    }
    
}