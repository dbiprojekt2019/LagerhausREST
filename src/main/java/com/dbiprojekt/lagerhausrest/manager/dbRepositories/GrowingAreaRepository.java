package com.dbiprojekt.lagerhausrest.manager.dbRepositories;

import com.dbiprojekt.lagerhausrest.data.databasePOJOs.Delivery;
import com.dbiprojekt.lagerhausrest.data.databasePOJOs.GrowingArea;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GrowingAreaRepository extends CrudRepository<GrowingArea, Integer> {
}