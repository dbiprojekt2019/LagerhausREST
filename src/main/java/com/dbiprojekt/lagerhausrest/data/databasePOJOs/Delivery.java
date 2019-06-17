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

    public Delivery(double weight, Date dateOfDelivery, int maturityID, int harvestMonthID, int wareHouseID){
        this.weight = weight;
        this.dateOfDelivery = dateOfDelivery;
        this.maturityID = maturityID;
        this.harvestMonthID = harvestMonthID;
        this.wareHouseID = wareHouseID;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "`DeliveryID`")
    private int deliveryID;

    @Column(name = "`Weight`")
    private double weight;
    @Column(name = "`DateOfDelivery`")
    private Date dateOfDelivery;
    @Column(name = "`MaturityID`")
    private int maturityID;
    @Column(name = "`HarvestMonthID`")
    private int harvestMonthID;
    @Column(name = "`WareHouseID`")
    private int wareHouseID;
}
