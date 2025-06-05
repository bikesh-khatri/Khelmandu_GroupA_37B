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
   private String venueName;


   public String getVenueName() {
    return venueName;
   }

   public void setVenueName(String venueName) {
    this.venueName = venueName;
   }

   private String venueLocation;

   public String getVenueLocation() {
    return venueLocation;
   }

   public void setVenueLocation(String venueLocation) {
    this.venueLocation = venueLocation;
   }

   private int venuePrice;

   public int getVenuePrice() {
    return venuePrice;
   }

   public void setVenuePrice(int venuePrice) {
    this.venuePrice = venuePrice;
   }

   private String venueStatus;

   public String getVenueStatus() {
    return venueStatus;
   }

   public void setVenueStatus(String venueStatus) {
    this.venueStatus = venueStatus;
   }

   private String venueImage;

   public String getVenueImage() {
    return venueImage;
   }

   public void setVenueImage(String venueImage) {
    this.venueImage = venueImage;
   }

   public Venue(String venueName,String venueImage,String venueStatus, String venueLocation, int venuePrice){
      this.venueImage = venueImage;
      this.venueLocation = venueLocation;
      this.venueName = venueName;
      this.venueStatus = venueStatus;
      this.venuePrice = venuePrice;
   }
}
