package com.example.houseagent.entity;

import javafx.beans.property.*;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.DoubleProperty;

import javafx.beans.property.*;

public class Property {
    private final IntegerProperty id;
    private final StringProperty type;
    private final DoubleProperty squareFt;
    private final IntegerProperty owner;
    private final DoubleProperty price;
    private final StringProperty address;
    private final IntegerProperty bedrooms;
    private final IntegerProperty bathrooms;
    private final IntegerProperty age;
    private final BooleanProperty balcony;
    private final BooleanProperty pool;
    private final BooleanProperty backyard;
    private final BooleanProperty garage;
    private final StringProperty description;

    public Property(int id, String type, double squareFt, int owner, double price, String address,
                    int bedrooms, int bathrooms, int age, boolean balcony, boolean pool,
                    boolean backyard, boolean garage, String description) {
        this.id = new SimpleIntegerProperty(id);
        this.type = new SimpleStringProperty(type);
        this.squareFt = new SimpleDoubleProperty(squareFt);
        this.owner = new SimpleIntegerProperty(owner);
        this.price = new SimpleDoubleProperty(price);
        this.address = new SimpleStringProperty(address);
        this.bedrooms = new SimpleIntegerProperty(bedrooms);
        this.bathrooms = new SimpleIntegerProperty(bathrooms);
        this.age = new SimpleIntegerProperty(age);
        this.balcony = new SimpleBooleanProperty(balcony);
        this.pool = new SimpleBooleanProperty(pool);
        this.backyard = new SimpleBooleanProperty(backyard);
        this.garage = new SimpleBooleanProperty(garage);
        this.description = new SimpleStringProperty(description);
    }

    // Getters and Setters for the properties

    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty typeProperty() {
        return type;
    }

    public DoubleProperty squareFtProperty() {
        return squareFt;
    }

    public IntegerProperty ownerProperty() {
        return owner;
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public StringProperty addressProperty() {
        return address;
    }

    public IntegerProperty bedroomsProperty() {
        return bedrooms;
    }

    public IntegerProperty bathroomsProperty() {
        return bathrooms;
    }

    public IntegerProperty ageProperty() {
        return age;
    }

    public BooleanProperty balconyProperty() {
        return balcony;
    }

    public BooleanProperty poolProperty() {
        return pool;
    }

    public BooleanProperty backyardProperty() {
        return backyard;
    }

    public BooleanProperty garageProperty() {
        return garage;
    }

    public StringProperty descriptionProperty() {
        return description;
    }
}
