/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author admin
 */
public class Venue {
    private int id;
    private String venueName;
    private String venueDecription;
    private String venueLocation;
    private int venuePrice;
    private String venueType;
    private String venueImage;
    private Long venueContact;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getVenueDecription() {
        return venueDecription;
    }

    public void setVenueDecription(String venueDecription) {
        this.venueDecription = venueDecription;
    }
    
    

    public String getVenueLocation() {
        return venueLocation;
    }

    public void setVenueLocation(String venueLocation) {
        this.venueLocation = venueLocation;
    }

    public int getVenuePrice() {
        return venuePrice;
    }

    public void setVenuePrice(int venuePrice) {
        this.venuePrice = venuePrice;
    }

    public String getVenueType() {
        return venueType;
    }

    public void setVenueType(String venueType) {
        this.venueType = venueType;
    }


    public String getVenueImage() {
        return venueImage;
    }

    public void setVenueImage(String venueImage) {
        this.venueImage = venueImage;
    }

    public Long getVenueContact() {
        return venueContact;
    }

    public void setVenueContact(Long venueContact) {
        this.venueContact = venueContact;
    }

    public Venue(int id, String venueName, String venueDecription, String venueLocation, int venuePrice, String venueType, String venueImage, Long venueContact) {
        this.id = id;
        this.venueName = venueName;
        this.venueDecription = venueDecription;
        this.venueLocation = venueLocation;
        this.venuePrice = venuePrice;
        this.venueType = venueType;
        this.venueImage = venueImage;
        this.venueContact = venueContact;
    }

   public Venue(String venueName,String venueImage, String venueLocation, int venuePrice, int venue_id){
      this.venueImage = venueImage;
      this.venueLocation = venueLocation;
      this.venueName = venueName;
      this.venuePrice = venuePrice;
      this.id = venue_id;
   }
}

