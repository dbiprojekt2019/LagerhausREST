package com.dbiprojekt.lagerhausrest.manager;

import com.dbiprojekt.lagerhausrest.data.Lagerhaus;
import com.dbiprojekt.lagerhausrest.exceptions.LagerhausDatabaseConnectionFailed;
import com.dbiprojekt.lagerhausrest.exceptions.LagerhausDatabaseStatementFailed;
import com.dbiprojekt.lagerhausrest.manager.database.DatabaseManager;
import com.dbiprojekt.lagerhausrest.manager.database.StoredProcedure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class LagerhausManager {

    @Autowired
    DatabaseManager databaseManager;

    public List<Lagerhaus> getAllLagerhausObjects() throws LagerhausDatabaseConnectionFailed, LagerhausDatabaseStatementFailed {
        List<Lagerhaus> lagerhausList = new ArrayList<>();

        List<Map<String, Object>> result = databaseManager.sendQueryToDatabase(
                StoredProcedure.GET_ALL_LAGERHAUS,
                new String[]{
                        databaseManager.COLL_LAGER_ID,
                        databaseManager.COLL_LAGER_DESCRIPTION
                    }
                );
        for (Map map : result) {
            Lagerhaus l = new Lagerhaus();
            l.setLagerId((int) map.get(databaseManager.COLL_LAGER_ID));
            l.setBezeichnung((String) map.get(databaseManager.COLL_LAGER_DESCRIPTION));
            lagerhausList.add(l);
        }

        return lagerhausList;
    }
}
