package ru.gb.phonebook;

public class Account {

    private String surname;
    private String phone;

    public Account(String surname, String phone){
        this.surname = surname;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
