package com.dbiprojekt.lagerhausrest.data.databasePOJOs;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "KindOfFruit")
public class KindOfFruit {

    protected KindOfFruit(){}

    public KindOfFruit(String description){
        this.Description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int FruitID;

    private String Description;
}
