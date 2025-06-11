package Controller;

import Dao.VenueDao;
import Model.Venue;
import View.BookingDialog;
import View.User_Dashboard;
import View.VenueCard;
import View.BookingDialog;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class userDashboardController {
    private final VenueDao venueDao = new VenueDao();
    private final User_Dashboard dashboardView;
    private int userID;
  
 
        public userDashboardController(User_Dashboard dashboardView,int id){
            this.dashboardView = dashboardView;
            this.userID = id;
            dashboardView.addBasketballListener(e -> new AddVenueListener("basketball"));
          dashboardView.addFutsalListener(e -> loadVenuesByType("futsal"));
          dashboardView.addCricksalListener(e -> loadVenuesByType("cricksal"));
          dashboardView.addTabletennisListener(e -> loadVenuesByType("tabletennis"));
            dashboardView.addBadmintonListener(new AddVenueListener("badminton"));
        }
public void open(){        
    this.dashboardView.setVisible(true);
}
    
public void close() {
        this.dashboardView.dispose();
}

        
        
    
class AddVenueListener implements ActionListener {        
                
        private String venueType;

        public AddVenueListener(String venueType) {
            this.venueType = venueType;
        }
    
        @Override
        public void actionPerformed(ActionEvent e) {            
        try {                     
            loadVenuesByType(venueType);
        } catch (Exception ex) {                
            System.out.println("Error loading venue: " + ex.getMessage());            
        }        
    }    
    
}


    private void loadVenuesByType(String type){
        
        List<Venue> venues = venueDao.getVenuesByType(type);
            dashboardView.getVenuePanel().removeAll();

            for (Venue venue : venues) {
               
                    VenueCard card = new VenueCard(venue,userID); 
                    
                    dashboardView.getVenuePanel().add(card);
                    JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
                    separator.setPreferredSize(new Dimension(dashboardView.getVenuePanel().getWidth(), 10));
                    dashboardView.getVenuePanel().add(separator);
                    card.addBookingListener(e -> {
                        JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(card);
                        BookingDialog dialog = new BookingDialog(parent,card.venue.getVenue_id());
                           dialog.setVisible(true);
                     });
                    

    }
            dashboardView.getVenuePanel().revalidate();
            dashboardView.getVenuePanel().repaint();
            
      


}
}
    
