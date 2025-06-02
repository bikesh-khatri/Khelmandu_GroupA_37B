package Controller;

import Model.Venue;
import View.VenueCard;

public class Dashboardcontroller {

    private void loadVenuesByType(String type) {
    venuePanel.removeAll();

    for (Venue v : allVenues) {
        if (v.getType().equalsIgnoreCase(type)) {
            VenueCard card = new VenueCard(); // custom JPanel
            card.setVenue(v);
            venuePanel.add(card);
        }
    }

    venuePanel.revalidate();
    venuePanel.repaint();
}
}
