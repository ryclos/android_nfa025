package com.example.user;

public class Personnes {

    private String lastname;
    private String name;
    private String email;
    private String birth;
    private String dateCreateAccount;

    public String getDateCreateAccount() {
        return dateCreateAccount;
    }

    public void setDateCreateAccount(String dateCreateAccount) {
        this.dateCreateAccount = dateCreateAccount;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }
}
