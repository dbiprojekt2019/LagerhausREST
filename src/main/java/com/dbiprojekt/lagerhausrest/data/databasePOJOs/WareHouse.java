package com.dbiprojekt.lagerhausrest.data.databasePOJOs;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "WareHouse")
public class WareHouse {

    protected WareHouse(){}

    public WareHouse(String description){
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int wareHouseId;

    private String description;
}
