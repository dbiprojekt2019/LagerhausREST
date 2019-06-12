package com.dbiprojekt.lagerhausrest.data.databasePOJOs;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "GrowingArea")
public class GrowingArea {

    protected GrowingArea(){}

    public GrowingArea(String description, double area, double seaLevel){
        this.Description = description;
        this.Area = area;
        this.SeaLevel = seaLevel;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int GrowingAreaID;

    private String Description;
    private double Area;
    private double SeaLevel;

}
