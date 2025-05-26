package Controller;

import Dao.Dao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import Model.Userdata;
import View.Signup;


public class controller {
    private final Dao Dao = new Dao();
    private final Signup userView;
    
    
    public controller(Signup userView) {
        this.userView= userView;
        userView.addAdduserListener(new AdduserListener());
    }
    
    public void open() {
        this.userView.setVisible(true);
        
    }
    public void close(){
        this.userView.dispose();
    }
    
    class AdduserListener implements ActionListener {
       
        public void actionPerformed(ActionEvent e) {
            try {
                String fname = userView.getfnamefield().getText();
                String lname = userView.getlnamefield().getText();
               long number = Long.parseLong(userView.getnumber().getText());
                String password = userView.getpasswordfield().getText();
                Userdata user = new Userdata(fname,lname, number, password);
                boolean check = Dao.checkuser(user);
                if(check) {
                    JOptionPane.showMessageDialog(userView,  "Duplicate user");
                }
                else {
                    Dao.signup(user);
                }
            }
            catch (Exception ex) {
                System.out.println("Error adding user: " + ex.getMessage());
            }
        }
    }
}