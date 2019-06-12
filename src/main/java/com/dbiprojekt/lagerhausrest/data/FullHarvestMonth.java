package com.dbiprojekt.lagerhausrest.data;

import com.dbiprojekt.lagerhausrest.data.databasePOJOs.Delivery;
import com.dbiprojekt.lagerhausrest.data.databasePOJOs.GrowingArea;
import com.dbiprojekt.lagerhausrest.data.databasePOJOs.HarvestMonth;
import lombok.Data;

import java.sql.Date;
import java.util.List;

//import java.util.Date;

@Data
public class FullHarvestMonth {
    private HarvestMonth harvestMonth;
    private GrowingArea growingArea;
}
