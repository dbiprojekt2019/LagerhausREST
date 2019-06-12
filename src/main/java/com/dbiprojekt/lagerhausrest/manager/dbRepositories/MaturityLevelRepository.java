package com.dbiprojekt.lagerhausrest.manager.dbRepositories;

import com.dbiprojekt.lagerhausrest.data.databasePOJOs.Delivery;
import com.dbiprojekt.lagerhausrest.data.databasePOJOs.MaturityLevel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MaturityLevelRepository extends CrudRepository<MaturityLevel, Integer> {
}