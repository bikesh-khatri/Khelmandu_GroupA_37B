package Controller;

import Dao.VenueDao;
import Model.Venue;
import View.BookingDialog;
import View.User_Dashboard;
import View.VenueCard;
import View.BookingDialog;
import View.User_profile;
import View.rulesView;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class userDashboardController {

    private final VenueDao venueDao = new VenueDao();
    private final User_Dashboard dashboardView;
    private final int userID;
    public String venueType;

    public userDashboardController(User_Dashboard dashboardView, int id) {
        this.dashboardView = dashboardView;
        this.userID = id;
        dashboardView.addBasketballListener(new AddVenueListener("basketball"));
        dashboardView.addFutsalListener(new AddVenueListener("futsal"));
        dashboardView.addCricksalListener(new AddVenueListener("criksal"));
        dashboardView.addTabletennisListener(new AddVenueListener("tabletennis"));
        dashboardView.addBadmintonListener(new AddVenueListener("badminton"));
        dashboardView.profileListener(new showProfile());
        dashboardView.rulesListener(new showRules());
    }

    public void open() {
        this.dashboardView.setVisible(true);
    }

    public void close() {
        this.dashboardView.dispose();
    }

    private  class showRules implements ActionListener {
        

        @Override
        public void actionPerformed(ActionEvent e) {
            if(venueType.isEmpty()){
                JOptionPane.showMessageDialog(dashboardView, "Please Select Game Type");
            }
            else{
                ArrayList<String> rules = venueDao.getRulesByGame(venueType);
                rulesView viewPanel = new rulesView(rules);

                JOptionPane.showMessageDialog(
                        dashboardView,
                        viewPanel,
                        "Rules for " + venueType,
                        JOptionPane.PLAIN_MESSAGE
                );
            }
            
            
        }

       
    }

    private class showProfile implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            User_profile panel = new User_profile();
            UserProfileController controller = new UserProfileController(userDashboardController.this,panel, venueDao.getUserById(userID));

            JOptionPane.showMessageDialog(
                    dashboardView,
                    panel, 
                    "User Profile",
                    JOptionPane.PLAIN_MESSAGE
            );

        }

    }

    class AddVenueListener implements ActionListener {

       private String venueType;

        public AddVenueListener(String venueType) {
            this.venueType = venueType;
            
                    
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            userDashboardController.this.venueType = this.venueType;
            System.out.println(userDashboardController.this.venueType);
            try {
                loadVenuesByType(venueType);
            } catch (Exception ex) {
                System.out.println("Error loading venue: " + ex.getMessage());
            }
        }

    }

    private void loadVenuesByType(String type) {

        List<Venue> venues = venueDao.getVenuesByType(type);
        dashboardView.getVenuePanel().removeAll();

        for (Venue venue : venues) {

            VenueCard card = new VenueCard(venue, userID);

            dashboardView.getVenuePanel().add(card);
            JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
            separator.setPreferredSize(new Dimension(dashboardView.getVenuePanel().getWidth(), 10));
            dashboardView.getVenuePanel().add(separator);
            card.addBookingListener((ActionEvent e) -> {
                BookingDialog bd = new BookingDialog();
                BookingController bc = new BookingController(bd, venue.getId(), userID);
                bc.open();
            });

        }
        dashboardView.getVenuePanel().revalidate();
        dashboardView.getVenuePanel().repaint();

    }
}
