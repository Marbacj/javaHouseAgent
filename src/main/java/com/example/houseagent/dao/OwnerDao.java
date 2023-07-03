package com.example.houseagent.dao;

import com.example.houseagent.entity.Owner;
import java.util.ArrayList;
import java.util.List;

public class OwnerDao {
    private List<Owner> Owner;

    public OwnerDao() {
        Owner = new ArrayList<>();
    }

    public void addOwner(Owner owner) {
        Owner.add(owner);
    }

    public List<Owner> getAllOwners() {
        return Owner;
    }
}



