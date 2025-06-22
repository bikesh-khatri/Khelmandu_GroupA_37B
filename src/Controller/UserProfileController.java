package Controller;

import Dao.Dao;
import Model.Userdata;
import View.LogIn;
import View.User_profile;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class UserProfileController {
    private final User_profile profile;
    private final Userdata user;
    private final userDashboardController parent;
    private final Dao uDao = new Dao();
    
    public UserProfileController(userDashboardController c,User_profile profile, Userdata  user){
        this.profile = profile;
        this.user = user;
        this.parent =  c;
        this.profile.editListener(new editSave());
        this.profile.deleteListener(new deleteUser());
        setValues();
    }
    
    public void open(){
        this.profile.setVisible(true);
    }
    
    public void close(){
        this.profile.setVisible(false);
    }
    
    public void setValues(){
        profile.First_name.setText(user.getF_name());
        profile.Last_name.setText(user.getL_name());
        profile.Ph_number.setText(Long.toString(user.getPh_number()));
        profile.Password.setText(user.getPassword());
    }

    private class deleteUser implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(uDao.deleteUserById(user.getId())){
                JOptionPane.showMessageDialog(profile, "Deleted Successfully");
                LogIn loginPage = new LogIn();
                logInController Controller = new logInController(loginPage);
                Controller.open();
                parent.close();
                
            }
        }

        
    }

    private class editSave implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(profile.Edit.getText().equals("Edit")){
                profile.Edit.setText("Save");
                setEditable(true);
            }
            else{
                user.setF_name(profile.First_name.getText());
                user.setL_name(profile.Last_name.getText());
                user.setPassword(profile.Password.getText());
                user.setPh_number(Long.parseLong(profile.Ph_number.getText()));
                if(uDao.updateUser(user)){
                    profile.Edit.setText("Edit");
                    setEditable(false);
                }else{
                    JOptionPane.showMessageDialog(profile, "CouldNot set");
                }
                
            }
        }

        
    }
    
    public void setEditable(boolean status){
        profile.First_name.setEditable(status);
        profile.Last_name.setEditable(status);
        profile.Ph_number.setEditable(status);
        profile.Password.setEditable(status);
    }
    
        
}