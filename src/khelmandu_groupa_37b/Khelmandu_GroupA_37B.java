/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package khelmandu_groupa_37b;

/**
 *
 * @author khatr
 */
public class Khelmandu_GroupA_37B {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         login loginFrame = new login();
         loginFrame.setVisible(true);
         loginFrame.pack();
         loginFrame.setLocationRelativeTo(null); 
         Signup SignupFrame = new Signup();
         SignupFrame.setVisible(true);
         SignupFrame.pack();
         SignupFrame.setLocationRelativeTo(null);
    }
    
}
