package com.dbiprojekt.lagerhausrest.manager.dbRepositories;

import com.dbiprojekt.lagerhausrest.data.databasePOJOs.Delivery;
import com.dbiprojekt.lagerhausrest.data.databasePOJOs.HarvestMonth;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HarvestMonthRepository extends CrudRepository<HarvestMonth, Integer> {
}