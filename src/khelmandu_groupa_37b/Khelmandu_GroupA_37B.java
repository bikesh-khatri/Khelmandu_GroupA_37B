/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package khelmandu_groupa_37b;

import View.SignUp;
import Controller.controller;
import Controller.logInController;
import View.LogIn;

/**
 *
 * @author khatr
 */
public class Khelmandu_GroupA_37B {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TOD    public static void main(String[] args) {

        LogIn loginPage = new LogIn();
        logInController Controller = new logInController(loginPage);
        Controller.open();
    }
}
