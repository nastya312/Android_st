package me.discgold.strangerthings.Models;

public class User {
    private String name, email, pass, phone;

    public User() {}

    public User(String name, String email, String pass, String phone) {
        this.email = email;
        this.name = name;
        this.pass = pass;
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPass() {
        return pass;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

}
