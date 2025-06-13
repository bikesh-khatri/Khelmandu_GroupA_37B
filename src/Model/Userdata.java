/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author khatr
 */
public class Userdata {
    private String f_name;
    private String l_name;
    private long ph_number;
    private String role;
    private String password;

    public Userdata(String f_name, String l_name, long ph_number, String role, String password) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.ph_number = ph_number;
        this.role = role;
        this.password = password;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public long getPh_number() {
        return ph_number;
    }

    public void setPh_number(long ph_number) {
        this.ph_number = ph_number;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    

 }
    

