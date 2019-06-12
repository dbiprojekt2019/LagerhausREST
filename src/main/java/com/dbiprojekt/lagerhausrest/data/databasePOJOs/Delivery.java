package com.dbiprojekt.lagerhausrest.data.databasePOJOs;

import lombok.Data;

import javax.persistence.*;
//import java.sql.Date;

import java.util.Date;

@Data
@Entity
@Table(name = "Delivery")
public class Delivery {

    public Delivery(){}

    public Delivery(int weight, Date dateOfDelivery, int maturityID, int harvestMonthID, int wareHouseID){
        this.Weight = weight;
        this.DateOfDelivery = dateOfDelivery;
        this.MaturityID = maturityID;
        this.HarvestMonthID = harvestMonthID;
        this.WareHouseID = wareHouseID;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int DeliveryID;

    private int Weight;
    private Date DateOfDelivery;
    private int MaturityID;
    private int HarvestMonthID;
    private int WareHouseID;
}
