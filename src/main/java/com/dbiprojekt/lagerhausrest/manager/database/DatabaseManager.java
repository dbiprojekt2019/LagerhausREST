package com.dbiprojekt.lagerhausrest.manager.database;

import com.dbiprojekt.lagerhausrest.exceptions.LagerhausDatabaseConnectionFailed;
import com.dbiprojekt.lagerhausrest.exceptions.LagerhausDatabaseStatementFailed;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public CollumnsSingleton collumns;

    //</editor-fold>

    public List<Map<String, Object>> sendQueryToDatabase(StoredProcedure proc, Object[] parameters, String[] neededCollumns)
            throws LagerhausDatabaseConnectionFailed, LagerhausDatabaseStatementFailed
    {
        getDatabaseConnection();

        String procedure = proc.name().toLowerCase() + "(";
        for (Object o :
                parameters) {
            procedure = procedure + "?,";
        }
        procedure = procedure.substring(0,procedure.lastIndexOf(",")) + ")";
        String statement = "{CALL "+procedure+"}";

        

        try {
            CallableStatement p = connection.prepareCall(statement);
            for (int i = 0; i < parameters.length; i++){
                if (parameters[i] instanceof String)
                    p.setString(i, (String)parameters[i]);
                if (parameters[i] instanceof Integer)
                    p.setInt(i, (Integer) parameters[i]);

            }
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
