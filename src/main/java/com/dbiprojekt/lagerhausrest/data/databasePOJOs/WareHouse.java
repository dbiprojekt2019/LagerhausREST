package com.dbiprojekt.lagerhausrest.data.databasePOJOs;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "WareHouse")
public class WareHouse {

    protected WareHouse(){}

    public WareHouse(String description){
        this.Description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int WareHouseId;

    private String Description;
}
