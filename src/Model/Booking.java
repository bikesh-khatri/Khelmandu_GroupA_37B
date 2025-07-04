/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;

/**
 *
 * @author admin
 */
public class Booking {
    private int user_id;
    
    public Booking(int venue_id,Date bookingDate, String timeSlot, String paymentType, int user_id) {
        this.venue_id = venue_id;
        this.bookingDate = bookingDate;
        this.timeSlot = timeSlot;
        this.paymentType = paymentType;
        this.user_id = user_id;
    }
    
     public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

     private int venue_id;
    public int getVenue_id() {
        return venue_id;
    }
     public void setVenue_id(int venue_id) {
         this.venue_id = venue_id;
     }

    private Date bookingDate;
    public Date getBookingDate() {
        return bookingDate;
    }
    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }


    private String timeSlot;
    public String getTimeSlot() {
        return timeSlot;
    }
    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    private String paymentType;
    public String getPaymentType() {
        return paymentType;
    }
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }


}
