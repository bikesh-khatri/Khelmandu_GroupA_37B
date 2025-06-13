/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


import Dao.Dao;
import View.*;
import Controller.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


/**
 *
 * @author khatr
 */
public class logInController {
    private final Dao userDao = new Dao();
    private final LogIn userView;

    public logInController(LogIn userView) {
        this.userView = userView;
        userView.addAddUserListener(new AddUserListener());
    }

    public void open() {
        this.userView.setVisible(true);
    }

    public void close() {
        this.userView.dispose();
    }
    
    class AddUserListener implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String phno = userView.getNumber();
                String password = userView.getPass();
                
                 if (phno.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Username is required.");
                    return;
                }
                if (password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Password is required.");
                    return;
                }
               String role = userDao.checkuser(Long.parseLong(phno));
               if(role.equals("null")){
                   JOptionPane.showMessageDialog(userView, "Invalid Credentials");
                   return;
               }
               
               int id = userDao.logIn(phno, password);
               if (role.equals("Admin")){
                   System.out.println("role is admin");
                   venueDashboard dashboard = new venueDashboard();
                   System.out.println("view called");
                   venueDashboardController c = new venueDashboardController(dashboard,id);
                   System.out.println("controlled called");
                   c.open();
                   close();
                   
               }else if (role.equals("Player")){
                   User_Dashboard dashboard = new User_Dashboard(id);
                   userDashboardController c = new userDashboardController(dashboard, id);
                   c.open();
                   close();
               }
               
              
                
            } catch (Exception ex) {
                System.out.println("Error Loggig user: " + ex.getMessage());
            }
        }
    }
    
}