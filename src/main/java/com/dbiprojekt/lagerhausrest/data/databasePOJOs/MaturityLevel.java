package com.dbiprojekt.lagerhausrest.data.databasePOJOs;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "MaturityLevel")
public class MaturityLevel {

    protected MaturityLevel(){}

    public MaturityLevel(int minStorageTime, int fruitID){
        this.minStorageTime = minStorageTime;
        this.fruitID = fruitID;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int maturityID;

    private int minStorageTime;
    private int fruitID;
}
