package Controller;

import Dao.VenueDao;
import Model.Venue;
import View.User_Dashboard;
import View.VenueCard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class userDashboardController {
    private final VenueDao venueDao = new VenueDao();
    private final User_Dashboard dashboardView;
  
 
        public userDashboardController(User_Dashboard dashboardView){
            this.dashboardView = dashboardView;
            dashboardView.addBasketballListener(e -> new AddVenueListener("basketball"));
          dashboardView.addFutsalListener(e -> loadVenuesByType("futsal"));
          dashboardView.addCricksalListener(e -> loadVenuesByType("cricksal"));
          dashboardView.addTabletennisListener(e -> loadVenuesByType("tabletennis"));
            dashboardView.addBadmintonListener(new AddVenueListener("badminton"));
        }
        public void open() {
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
               
                    VenueCard card = new VenueCard(); // custom JPanel
                    card.setVenue(venue);
                    dashboardView.getVenuePanel().add(card);
                
            

            dashboardView.getVenuePanel().revalidate();
            dashboardView.getVenuePanel().repaint();
      
    }


}
}
    
