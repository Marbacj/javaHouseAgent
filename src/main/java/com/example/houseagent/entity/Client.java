package com.example.houseagent.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class Client {
    private IntegerProperty id;
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty phone;
    private StringProperty email;
    private StringProperty address;
    private StringProperty name;
    private StringProperty status;
    public Client(int id, String firstName, String lastName, String phone, String email, String address) {
        this.id = new SimpleIntegerProperty(id);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.phone = new SimpleStringProperty(phone);
        this.email = new SimpleStringProperty(email);
        this.address = new SimpleStringProperty(address);
    }

    // Getters and Setters for the properties

    public IntegerProperty idProperty() {
        return id;
    }

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




    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty statusProperty() {
        return status;
    }

    public int getId() {
        return id.get();
    }
}

