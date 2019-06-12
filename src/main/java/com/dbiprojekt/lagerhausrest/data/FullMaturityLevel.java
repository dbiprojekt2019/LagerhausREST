package com.dbiprojekt.lagerhausrest.data;

import com.dbiprojekt.lagerhausrest.data.databasePOJOs.GrowingArea;
import com.dbiprojekt.lagerhausrest.data.databasePOJOs.HarvestMonth;
import com.dbiprojekt.lagerhausrest.data.databasePOJOs.KindOfFruit;
import com.dbiprojekt.lagerhausrest.data.databasePOJOs.MaturityLevel;
import lombok.Data;

import java.util.List;

//import java.util.Date;

@Data
public class FullMaturityLevel {
    private MaturityLevel maturityLevel;
    private KindOfFruit kindOfFruit;
}
