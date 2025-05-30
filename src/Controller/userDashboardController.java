/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.User_Dashboard;

/**
 *
 * @author khatr
 */
public class userDashboardController {

    private final User_Dashboard userView;

    public userDashboardController(User_Dashboard userView) {
        this.userView = userView;
        
    }

    public void open() {
        this.userView.setVisible(true);
    }

    public void close() {
        this.userView.dispose();
    }
    
}
    