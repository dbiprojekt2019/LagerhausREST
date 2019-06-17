package com.dbiprojekt.lagerhausrest.data.databasePOJOs;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "HarvestMonth")
public class HarvestMonth {

    protected HarvestMonth(){}

    public HarvestMonth(String description, int numberRainyDays, int numberSunnyDays, int growingAreaID){
        this.description = description;
        this.numberRainyDays = numberRainyDays;
        this.numberSunnyDays = numberSunnyDays;
        this.growingAreaID = growingAreaID;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int harvestMonthID;

    private String description;
    private int numberRainyDays;
    private int numberSunnyDays;
    private int growingAreaID;

}
