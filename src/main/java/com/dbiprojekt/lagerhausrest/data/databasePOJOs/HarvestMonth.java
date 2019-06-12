package com.dbiprojekt.lagerhausrest.data.databasePOJOs;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "HarvestMonth")
public class HarvestMonth {

    protected HarvestMonth(){}

    public HarvestMonth(String description, int numberRainyDays, int numberSunnyDays, int growingAreaID){
        this.Description = description;
        this.NumberRainyDays = numberRainyDays;
        this.NumberSunnyDays = numberSunnyDays;
        this.GrowingAreaID = growingAreaID;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int HarvestMonthID;

    private String Description;
    private int NumberRainyDays;
    private int NumberSunnyDays;
    private int GrowingAreaID;

}
