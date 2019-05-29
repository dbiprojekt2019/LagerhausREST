package com.dbiprojekt.lagerhausrest.dataServices;

import com.dbiprojekt.lagerhausrest.data.Erntemonat;
import com.dbiprojekt.lagerhausrest.data.Lagerhaus;
import com.dbiprojekt.lagerhausrest.data.Lieferung;
import com.dbiprojekt.lagerhausrest.data.Reifegrad;
import com.dbiprojekt.lagerhausrest.exceptions.LagerhausDatabaseConnectionFailed;
import com.dbiprojekt.lagerhausrest.exceptions.LagerhausDatabaseStatementFailed;
import com.dbiprojekt.lagerhausrest.exceptions.runtimeException.LagerhausBadRequestException;
import com.dbiprojekt.lagerhausrest.exceptions.runtimeException.LagerhausDatabaseException;
import com.dbiprojekt.lagerhausrest.manager.LagerhausManager;
import com.dbiprojekt.lagerhausrest.restData.ErntemonatResourceDTO;
import com.dbiprojekt.lagerhausrest.restData.LagerhausResourceDTO;
import com.dbiprojekt.lagerhausrest.restData.LieferungResourceDTO;
import com.dbiprojekt.lagerhausrest.restData.ReifegradResourceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LagerhausDataService {

    @Autowired
    private LagerhausManager manager;

    //<editor-fold desc="Validity Checks">

    private void checkLagerhausValid(LagerhausResourceDTO lagerhaus){

        if (lagerhaus == null)
            throw new LagerhausBadRequestException("Foo cannot be null");

    }
    private void checkLieferungValid(LieferungResourceDTO lieferung){

        if (lieferung == null)
            throw new LagerhausBadRequestException("Foo cannot be null");

    }
    private void checkReifegradValid(ReifegradResourceDTO reifegrad){

        if (reifegrad == null)
            throw new LagerhausBadRequestException("Foo cannot be null");

    }
    private void checkErntemonatValid(ErntemonatResourceDTO erntemonat){

        if (erntemonat == null)
            throw new LagerhausBadRequestException("Foo cannot be null");

    }

    //</editor-fold>

    //<editor-fold desc="DTO to Object converters">

    /*
    private Lagerhaus convertLagerhausDTOToLagerhaus(LagerhausResourceDTO dto)
    {
        return null;
    }
    */

    private Lieferung convertLieferungDTOToLieferung(LieferungResourceDTO dto)
    {
        return null;
    }

    /*
    private Erntemonat convertErntemonatDTOToErntemonat(ErntemonatResourceDTO dto)
    {
        return null;
    }

    private Reifegrad converReifegradDTOToReifegrad(ReifegradResourceDTO dto)
    {
        return null;
    }
    */

    //</editor-fold>

    //<editor-fold desc="Object to Resource converters">

    private LagerhausResourceDTO convertLagerhausToLagerhausResource(Lagerhaus lagerhaus)
    {
        return null;
    }

    private LieferungResourceDTO convertLieferungToLieferungResource(Lieferung lieferung)
    {
        return null;
    }

    private ReifegradResourceDTO convertReifegradToReifegradResource (Reifegrad reifegrad)
    {
        return null;
    }

    private ErntemonatResourceDTO convertErntemonatToErntemonatResource(Erntemonat erntemonat)
    {
        return null;
    }

    //</editor-fold>


    public List<LagerhausResourceDTO> getAllLagerhausObjects() {
        List<Lagerhaus> lagerhausList = null;
        try {
            lagerhausList = manager.getAllLagerhausObjects();
        } catch (LagerhausDatabaseConnectionFailed lagerhausDatabaseConnectionFailed) {
            lagerhausDatabaseConnectionFailed.printStackTrace();
            throw new LagerhausDatabaseException("Database connection failed");
        } catch (LagerhausDatabaseStatementFailed lagerhausDatabaseStatementFailed) {
            lagerhausDatabaseStatementFailed.printStackTrace();
            throw new LagerhausDatabaseException("Request to database failed");
        }

        List<LagerhausResourceDTO> result = new ArrayList<>();

        for(Lagerhaus l : lagerhausList){
            result.add(convertLagerhausToLagerhausResource(l));
        }

        return result;
    }

    public List<LieferungResourceDTO> getLieferungOfLagerhaus(int lagerhausId) {
        List<Lieferung> lieferungList = null;
        try {
            lieferungList = manager.getLieferungOfLagerhaus(lagerhausId);
        } catch (LagerhausDatabaseConnectionFailed lagerhausDatabaseConnectionFailed) {
            lagerhausDatabaseConnectionFailed.printStackTrace();
            throw new LagerhausDatabaseException("Database connection failed");
        } catch (LagerhausDatabaseStatementFailed lagerhausDatabaseStatementFailed) {
            lagerhausDatabaseStatementFailed.printStackTrace();
            throw new LagerhausDatabaseException("Request to database failed");
        }

        List<LieferungResourceDTO> result = new ArrayList<>();

        for(Lieferung l : lieferungList){
            result.add(convertLieferungToLieferungResource(l));
        }

        return result;
    }

    public ReifegradResourceDTO getReifegrad(int reifeId) {
        Reifegrad reife = null;
        try {
            reife = manager.getReifegrad(reifeId);
        } catch (LagerhausDatabaseConnectionFailed lagerhausDatabaseConnectionFailed) {
            lagerhausDatabaseConnectionFailed.printStackTrace();
            throw new LagerhausDatabaseException("Database connection failed");
        } catch (LagerhausDatabaseStatementFailed lagerhausDatabaseStatementFailed) {
            lagerhausDatabaseStatementFailed.printStackTrace();
            throw new LagerhausDatabaseException("Request to database failed");
        }

        return convertReifegradToReifegradResource(reife);
    }

    public ErntemonatResourceDTO getErntemonat(int erntemonatId) {
        Erntemonat monat = null;

        /*
        try {
            monat = manager.getErntemonat(erntemonatId);
        } catch (LagerhausDatabaseConnectionFailed lagerhausDatabaseConnectionFailed) {
            lagerhausDatabaseConnectionFailed.printStackTrace();
            throw new LagerhausDatabaseException("Database connection failed");
        } catch (LagerhausDatabaseStatementFailed lagerhausDatabaseStatementFailed) {
            lagerhausDatabaseStatementFailed.printStackTrace();
            throw new LagerhausDatabaseException("Request to database failed");
        }
        */

        monat = manager.getErntemonat(erntemonatId);

        return convertErntemonatToErntemonatResource(monat);
    }

    public LieferungResourceDTO insertLieferung(int lieferungId, LieferungResourceDTO lieferung) {
        Lieferung result = null;

        /*
        try {
            result = manager.insertLieferung(lieferungId, convertLieferungDTOToLieferung(lieferung));
        } catch (LagerhausDatabaseConnectionFailed lagerhausDatabaseConnectionFailed) {
            lagerhausDatabaseConnectionFailed.printStackTrace();
            throw new LagerhausDatabaseException("Database connection failed");
        } catch (LagerhausDatabaseStatementFailed lagerhausDatabaseStatementFailed) {
            lagerhausDatabaseStatementFailed.printStackTrace();
            throw new LagerhausDatabaseException("Request to database failed");
        }
        */
        result = manager.insertLieferung(lieferungId, convertLieferungDTOToLieferung(lieferung));

        return convertLieferungToLieferungResource(result);
    }

    public LieferungResourceDTO updateLieferung(int lieferungId, LieferungResourceDTO lieferung) {
        Lieferung result = null;

        /*
        try {
            result = manager.updateLieferung(lieferungId, convertLieferungDTOToLieferung(lieferung));
        } catch (LagerhausDatabaseConnectionFailed lagerhausDatabaseConnectionFailed) {
            lagerhausDatabaseConnectionFailed.printStackTrace();
            throw new LagerhausDatabaseException("Database connection failed");
        } catch (LagerhausDatabaseStatementFailed lagerhausDatabaseStatementFailed) {
            lagerhausDatabaseStatementFailed.printStackTrace();
            throw new LagerhausDatabaseException("Request to database failed");
        }
        */

        result = manager.updateLieferung(lieferungId, convertLieferungDTOToLieferung(lieferung));

        return convertLieferungToLieferungResource(result);
    }

    public void deleteLieferung(int lieferungId) {
        manager.deleteLieferung(lieferungId);
    }
}


