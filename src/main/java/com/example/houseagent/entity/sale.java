package com.example.houseagent.entity;

import javafx.beans.property.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class sale {
    private IntegerProperty propertyId;
    private IntegerProperty clientId;
    private DoubleProperty finalPrice;
    private StringProperty date;

    public sale(int propertyId, int clientId, double finalPrice, String date) {
        this.propertyId = new SimpleIntegerProperty(propertyId);
        this.clientId = new SimpleIntegerProperty(clientId);
        this.finalPrice = new SimpleDoubleProperty(finalPrice);
        this.date = new SimpleStringProperty(date);
    }

    // Getters and Setters for the properties

    public IntegerProperty propertyIdProperty() {
        return propertyId;
    }

    public IntegerProperty clientIdProperty() {
        return clientId;
    }

    public DoubleProperty finalPriceProperty() {
        return finalPrice;
    }

    public StringProperty dateProperty() {
        return date;
    }
}

