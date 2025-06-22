/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


import Dao.BookingDao;
import Model.Booking;
import View.BookingDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

import javax.swing.JOptionPane;
import javax.swing.JSpinner;

public class BookingController {

    private final BookingDialog dialog;
    private final BookingDao bookingDAO;
    private final int venueId;
    private final int userId;

  public BookingController(BookingDialog dialog, int venueId, int userId) {
    this.dialog = dialog;
    this.venueId = venueId;
    this.userId = userId;
    this.bookingDAO = new BookingDao(); // FIXED
    this.dialog.addBookBtnListener(new BookingListener());
    
}

    
    public void open(){
        this.dialog.setVisible(true);
    }
    
    public void close(){
        this.dialog.setVisible(false);
    }

    // Inner class for handling the book button
    class BookingListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("dksgios");
            try {
                
                Date selectedDate = (Date) dialog.getSelectedDate();
                System.out.println(dialog.getSelectedDate().toString());
                String timeSlot = dialog.getSelectedTimeSlot();
                String paymentMethod = dialog.getSelectedPaymentMethod();
                Booking booking = new Booking(venueId, selectedDate, timeSlot, paymentMethod, userId);
                if(bookingDAO.isAvailable(booking)){
                boolean success = bookingDAO.insertBooking(booking);
                
                if (success ){
                        JOptionPane.showMessageDialog(dialog,"Booking successful!");
                        dialog.dispose(); // close dialog}
                } 
                else {
                    JOptionPane.showMessageDialog(dialog,"Booking failed.");
                }
                }
                else{
                    JOptionPane.showMessageDialog(dialog, "UnAvailable for this Time");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog,"Error: " + ex.getMessage());
            }
        }
    }
}
