package Controller;

import View.User_profile;
import javax.swing.JFrame;

public class UserProfileController {
    
    public void open() {
        JFrame profileFrame = new JFrame("User Profile");
        profileFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        profileFrame.setSize(400, 400); 
        profileFrame.setLocationRelativeTo(null);  
        
        User_profile userProfilePanel = new User_profile();
        
        profileFrame.add(userProfilePanel);
        
        profileFrame.setVisible(true);
    }
}