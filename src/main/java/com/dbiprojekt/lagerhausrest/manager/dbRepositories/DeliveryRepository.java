package com.dbiprojekt.lagerhausrest.manager.dbRepositories;

import com.dbiprojekt.lagerhausrest.data.databasePOJOs.Delivery;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeliveryRepository extends CrudRepository<Delivery, Integer> {
    List<Delivery> findByWareHouseID(int warehouseID);
}