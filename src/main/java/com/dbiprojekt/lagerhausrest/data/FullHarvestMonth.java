package com.dbiprojekt.lagerhausrest.data;

import com.dbiprojekt.lagerhausrest.data.databasePOJOs.GrowingArea;
import com.dbiprojekt.lagerhausrest.data.databasePOJOs.HarvestMonth;
import lombok.Data;

@Data
public class FullHarvestMonth {
    private HarvestMonth harvestMonth;
    private GrowingArea growingArea;
}
