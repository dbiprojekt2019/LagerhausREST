package com.dbiprojekt.lagerhausrest.data.databasePOJOs;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "GrowingArea")
public class GrowingArea {

    protected GrowingArea(){}

    public GrowingArea(String description, double area, double seaLevel){
        this.description = description;
        this.area = area;
        this.seaLevel = seaLevel;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int growingAreaID;

    private String description;
    private double area;
    private double seaLevel;

}
