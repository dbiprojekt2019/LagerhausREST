package com.dbiprojekt.lagerhausrest.data.databasePOJOs;

import lombok.Data;

import javax.persistence.*;
//import java.sql.Date;

import java.util.Date;

@Data
@Entity
@Table(name = "Delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int DeliveryID;

    private int Weight;
    private Date DateOfDelivery;
    private int MaturityID;
    private int HarvestMonthID;
    private int WareHouseID;
}
