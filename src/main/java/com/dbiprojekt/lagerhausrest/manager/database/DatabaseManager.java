package com.dbiprojekt.lagerhausrest.manager.database;

import com.dbiprojekt.lagerhausrest.exceptions.LagerhausDatabaseConnectionFailed;
import com.dbiprojekt.lagerhausrest.exceptions.LagerhausDatabaseStatementFailed;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DatabaseManager {

    private Connection connection = null;

    //<editor-fold desc="Database Strings">

    //TODO change storedProcedure names

    public final String COLL_LAGER_ID = "LagerId";
    public final String COLL_LAGER_DESCRIPTION = "Bezeichnung";

    //</editor-fold>

    public List<Map<String, Object>> sendQueryToDatabase(StoredProcedure proc, String[] neededCollumns)
            throws LagerhausDatabaseConnectionFailed, LagerhausDatabaseStatementFailed
    {
        getDatabaseConnection();

        String statement = "{CALL "+proc.name().toLowerCase()+"}";

        try {
            CallableStatement p = connection.prepareCall(statement); //TODO change SQL
            p.setInt(1, 123);
            ResultSet resultSet = p.executeQuery();

            List<Map<String, Object>> result = new ArrayList<>();
            while (resultSet.next()){
                Map<String, Object> m = new HashMap<>();
                for (String c : neededCollumns) {
                    m.put(c, resultSet.getObject(c));
                }
                result.add(m);
            }
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new LagerhausDatabaseStatementFailed("Execution of stored procedure: "+proc.name().toLowerCase()+" failed" +
                    "\n\r\n\rErrorMessage: "+e.getMessage());
        }
        //<editor-fold desc="unused code for normal query">
        /*
        String statement = "getAllLagerhaus";

        try {
            PreparedStatement p = connection.prepareStatement(statement);
            p.setInt(1, 123);
            ResultSet resultSet = p.executeQuery();

            List<Map<String, Object>> result = new ArrayList<>();
            while (resultSet.next()){
                Map<String, Object> m = new HashMap<>();
                for (String c : collumns) {
                    m.put(c, resultSet.getObject(c));
                }
                result.add(m);
            }
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new LagerhausDatabaseStatementFailed("Execution of statement: "+statement+" failed" +
                    "\n\r\n\rErrorMessage: "+e.getMessage());
        }
        */
        //</editor-fold>
    }

    private void getDatabaseConnection() throws LagerhausDatabaseConnectionFailed {
        if (connection == null){
            try {
                connection = DriverManager.getConnection("jdbc:mariadb://localhost:1234/DB?user=root&password=pass"); //TODO change connection
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new LagerhausDatabaseConnectionFailed("Database connection Error: "+e.getMessage());
            }
        }
    }

}
