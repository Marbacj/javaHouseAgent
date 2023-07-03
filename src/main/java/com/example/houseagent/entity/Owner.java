package com.example.houseagent.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Owner {
    private IntegerProperty id;
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty phone;
    private StringProperty email;
    private StringProperty address;
    public Owner(){};
    public Owner (String firstName, String lastName, String phone, String email, String address) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.phone = new SimpleStringProperty(phone);
        this.email = new SimpleStringProperty(email);
        this.address = new SimpleStringProperty(address);
    }
    public Owner (int id,String firstName, String lastName, String phone, String email, String address) {
        this.id = new SimpleIntegerProperty(id);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.phone = new SimpleStringProperty(phone);
        this.email = new SimpleStringProperty(email);
        this.address = new SimpleStringProperty(address);
    }

    // Getters and Setters for the properties

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty addressProperty() {
        return address;
    }
    public void setFirstName(String firstName){
        this.firstName.set(firstName);
    }
    public void setLastName(String lastName){
        this.lastName.set(lastName);
    }
    public void setPhone(String phone){
        this.phone.set(phone);
    }
    public void setEmail(String email){
        this.email.set(email);
    }
    public void setAddress(String address){
        this.address.set(address);
    }
    public void setId(int id){
        this.id.set(id);
    }

    public int  getId() {
        return id.get();
    }

    public String getFirstName() {
        return firstName.get();
    }
    public String getLastName() {
        return lastName.get();
    }
    public String getPhone() {
        return phone.get();
    }
    public String getEmail() {
        return email.get();
    }
    public String getAddress(){
        return address.get();
    }


}

