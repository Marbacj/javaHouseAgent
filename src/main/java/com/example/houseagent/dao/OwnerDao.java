package com.example.houseagent.dao;

import com.example.houseagent.entity.Owner;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

import java.util.ArrayList;
import java.util.List;

import java.util.List;

public interface OwnerDao {
    void addOwner(Owner owner);
    List<Owner> getAllOwners();

}


