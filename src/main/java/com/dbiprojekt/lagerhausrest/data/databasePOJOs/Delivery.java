package com.dbiprojekt.lagerhausrest.data.databasePOJOs;

import lombok.Data;
import javax.persistence.*;
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
    private int deliveryID;

    private double weight;
    private Date dateOfDelivery;
    private int maturityID;
    private int harvestMonthID;
    private int wareHouseID;
}
