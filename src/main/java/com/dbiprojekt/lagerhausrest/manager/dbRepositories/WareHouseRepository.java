package com.dbiprojekt.lagerhausrest.manager.dbRepositories;

import com.dbiprojekt.lagerhausrest.data.databasePOJOs.Delivery;
import com.dbiprojekt.lagerhausrest.data.databasePOJOs.WareHouse;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WareHouseRepository extends CrudRepository<WareHouse, Integer> {
}