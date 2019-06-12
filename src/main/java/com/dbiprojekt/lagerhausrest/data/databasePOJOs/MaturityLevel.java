package com.dbiprojekt.lagerhausrest.data.databasePOJOs;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "MaturityLevel")
public class MaturityLevel {

    protected MaturityLevel(){}

    public MaturityLevel(int minStorageTime, int fruitID){
        this.MinStorageTime = minStorageTime;
        this.FruitID = fruitID;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int MaturityID;

    private int MinStorageTime;
    private int FruitID;
}
