/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.VenueDao;
import Model.*;
import View.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author thismac
 */
public class venueDashboardController {
    private final VenueDao vDao = new VenueDao();
    private final venueDashboard dashboard;
    private final int id;
    private Userdata user;
    private Venue venue;
     private static final String UPLOAD_DIR = "Images";
    
    public venueDashboardController(venueDashboard dashboard, int id){
        this.dashboard = dashboard;
        this.id = id;
        this.dashboard.addEditListener(new editAndSave());
        this.dashboard.imageClickListener(new addImage());
        new File(UPLOAD_DIR).mkdirs();
        getSet();
    }
    
    public void open(){
        this.dashboard.setVisible(true);
    }
    
    public void close(){
        this.dashboard.setVisible(false);
    }
    
    
    public void getSet(){
        user = vDao.getUserById(id);
        venue = vDao.getVenueById(id);
        
        dashboard.VenueName.setText(venue.getVenueName());
        dashboard.venueName.setText(venue.getVenueName());
        dashboard.venueDescription.setText(venue.getVenueDecription());
        dashboard.venueContact.setText(Long.toString(venue.getVenueContact()));
        dashboard.venueLocation.setText(venue.getVenueLocation());
        dashboard.venuePrice.setText(Integer.toString(venue.getVenuePrice()));
        dashboard.venueType.setSelectedItem(venue.getVenueType());
        
        String path = venue.getVenueImage();
        if (path != null && !path.isEmpty()) {
            try {
                ImageIcon icon = new ImageIcon(path);
                dashboard.VenueIcon.setIcon(icon);
            } catch (Exception e) {
               
            }
        } 
    }
    
    public void updateInfo(){
        venue.setVenueName(dashboard.venueName.getText());
        venue.setVenueDecription(dashboard.venueDescription.getText());
        venue.setVenueContact(Long.parseLong(dashboard.venueContact.getText()));
        venue.setVenueLocation(dashboard.venueLocation.getText());
        venue.setVenuePrice(Integer.parseInt(dashboard.venuePrice.getText()));
        venue.setVenueType((String) dashboard.venueType.getSelectedItem());
        if(vDao.updateVenue(venue)){
            JOptionPane.showMessageDialog(dashboard,"Updated Sucessfully");
            getSet();
        }
        else{
            JOptionPane.showMessageDialog(dashboard,"Could not Update");
        }
    
    }
    
    public void setEditable(boolean edit){
        dashboard.venueName.setEditable(edit);
        dashboard.venueDescription.setEditable(edit);
        dashboard.venueContact.setEditable(edit);
        dashboard.venueLocation.setEditable(edit);
        dashboard.venuePrice.setEditable(edit);
        dashboard.venueType.setEditable(edit);
        
                
    }

 

    private class editAndSave implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
           String bLabel = dashboard.editBtn.getText();
           if(bLabel.equals("Edit")){
               dashboard.editBtn.setText("Save");
               setEditable(true);
           }else{
               updateInfo();
               dashboard.editBtn.setText("Edit");
               setEditable(false);
               
           }
        }
        
    }
    
            
    private class addImage extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "png", "jpg", "jpeg"));
            int result = fileChooser.showOpenDialog(dashboard);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String previous = venue.getVenueImage();
                try {
                    BufferedImage originalImage = ImageIO.read(selectedFile);
                    BufferedImage resizedImage = resizeImage(originalImage, 500, 200);

                    String fileName = id + "_" + selectedFile.getName();
                    Path destination = Paths.get(UPLOAD_DIR, fileName);
                    File outputFile = destination.toFile();
                    ImageIO.write(resizedImage, "png", outputFile);
                    venue.setVenueImage(destination.toString());
                    
                    ImageIcon icon = new ImageIcon(destination.toString());
                    dashboard.VenueIcon.setIcon(icon);

                    if (previous != null) {
                        File existingLogo = new File(previous);
                        existingLogo.delete();
                    }

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error uploading logo: " + ex.getMessage());
                }
            }

        }

        private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
            // Scale image to 80x80 while preserving aspect ratio
            Image scaledImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
            BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
            resizedImage.getGraphics().drawImage(scaledImage, 0, 0, null);
            return resizedImage;
        }
    }
}
