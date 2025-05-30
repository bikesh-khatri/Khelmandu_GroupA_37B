/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package khelmandu_groupa_37b;
import View.SignUp;
import Controller.controller;

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
 
        SignUp signupForm = new SignUp();
        controller Controller = new controller(signupForm);
        Controller.open();
    }    }
   
    

