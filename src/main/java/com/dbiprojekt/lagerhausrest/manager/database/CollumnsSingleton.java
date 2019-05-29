package com.dbiprojekt.lagerhausrest.manager.database;

import lombok.Data;

@Data
public class CollumnsSingleton {

    //public final String TABELLE_SPALTENNAME = "SpaltennameInDatenbank";

    //Warehouse
    public final String WAREHOUSE_ID = "WareHouseID";
    public final String WAREHOUSE_DESCRIPTION = "Description";

    //Delivery
    public final String DELIVERY_ID = "DeliveryID";
    public final String DELIVERY_WEIGHT_TONS = "Weight";
    public final String DELIVERY_DATE  = "DateOfDelivery";
    public final String DELIVERY_HARVESTMONTH_FK_ID = "HarvestMonthID";
    public final String DELIVERY_MATURITY_FK_ID = "MaturityID";
    public final String DELIVERY_WAREHOUSE_FK_ID = "WareHouseID";

    //HarvestMonth
    public final String HARVESTHMONTH_ID = "HarvestMonthID";
    public final String HARVESTMONTH_DESCRIPTION = "Description";
    public final String HARVESTHMONTH_NUMBER_RAINY_DAYS = "NumberRainyDays";
    public final String HARVESTHMONTH_NUMBER_SUNNY_DAYS = "NumberSunnyDays";
    public final String HARVESTHMONTH_GROWING_AREA_FK_ID = "GrowingAreaID";

    //GrowingArea
    public final String GROWINGAREA_ID = "GrowingAreaID";
    public final String GROWINGAREA_DESCRIPTION = "Description";
    public final String GROWINGAREA_AREA_SQUAREMETERS = "Area";
    public final String GROWINGAREA_SEALEVEL = "SeaLevel";

    //MaturityLevel
    public final String MATURITY_ID = "MaturityID";
    public final String MATURITY_MIN_STORAGE_TIME = "MinStorageTime";
    public final String MATURITY_FRUIT_FK_ID = "FruitID";

    //KindOfFruit
    public final String FRUIT_ID = "FruitID";
    public final String FRUIT_DESCRIPTION = "Description";
}