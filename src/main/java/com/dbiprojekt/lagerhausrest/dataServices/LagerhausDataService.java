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

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class LagerhausDataService {

    @Autowired
    private LagerhausManager manager;

    //<editor-fold desc="Validity Checks">

    private void checkLagerhausValid(LagerhausResourceDTO lagerhaus){

        if (lagerhaus == null)
            throw new LagerhausBadRequestException("Lagerhaus cannot be null");

        if (lagerhaus.getLagerId() <= 0)
            throw new LagerhausBadRequestException("LagerhausID is invalid");

    }
    private void checkLieferungValid(LieferungResourceDTO lieferung){

        if (lieferung == null)
            throw new LagerhausBadRequestException("Lieferung cannot be null");

        if (lieferung.getLieferungsId() <= 0)
            throw new LagerhausBadRequestException("LieferungsID is invalid");

        if (lieferung.getUmfangInTonnen() < 0)
            throw new LagerhausBadRequestException("UmfangInTonnen is invalid");
    }
    private void checkReifegradValid(ReifegradResourceDTO reifegrad){

        if (reifegrad == null)
            throw new LagerhausBadRequestException("Reifegrad cannot be null");

        if (reifegrad.getReifegradID() <= 0)
            throw new LagerhausBadRequestException("ReifegradID is invalid");

        if (reifegrad.getMindestlagerdauerInTagen() < 0)
            throw new LagerhausBadRequestException("MindestlagerdauerInTagen is invalid");

    }
    private void checkErntemonatValid(ErntemonatResourceDTO erntemonat){

        if (erntemonat == null)
            throw new LagerhausBadRequestException("Erntemonat cannot be null");

        if (erntemonat.getErntemonatId() <= 0)
            throw new LagerhausBadRequestException("ErntemonatId is invalid");

        if (erntemonat.getAnzahlRegentage() < 0)
            throw new LagerhausBadRequestException("AnzahlRegentage is invalid");

        if (erntemonat.getAnzahlSonnentage() < 0)
            throw new LagerhausBadRequestException("AnzahlSonnentage is invalid");

        if (erntemonat.getFlaeche() < 0)
            throw new LagerhausBadRequestException("Flaeche is invalid");
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
        Lieferung l = new Lieferung();
        l.setLieferungsId(dto.getLieferungsId());
        l.setUmfangInTonnen(dto.getUmfangInTonnen());
        try {
            java.util.Date utilDate =  new SimpleDateFormat("dd.MM.yyyy").parse(dto.getDatumEinlagerung());
            l.setDatumEinlagerung(new Date(utilDate.getTime()));
        } catch (ParseException e) {
            throw new LagerhausBadRequestException("DatumEinlagerung is invalid");
        }
        l.setReifegradId(dto.getReifegradId());
        l.setErntemonatId(dto.getErntemonatId());
        l.setLagerId(dto.getLagerId());
        return l;
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

    private LagerhausResourceDTO convertLagerhausToLagerhausResource(Lagerhaus lagerhaus){
        LagerhausResourceDTO dto = new LagerhausResourceDTO();
        dto.setLagerId(lagerhaus.getLagerId());
        dto.setBezeichnung(lagerhaus.getBezeichnung());
        return dto;
    }

    private LieferungResourceDTO convertLieferungToLieferungResource(Lieferung lieferung){
        LieferungResourceDTO dto = new LieferungResourceDTO();
        dto.setLieferungsId(lieferung.getLieferungsId());
        dto.setUmfangInTonnen(lieferung.getUmfangInTonnen());
        java.util.Date utilDate = new java.util.Date(lieferung.getDatumEinlagerung().getTime());
        dto.setDatumEinlagerung(new SimpleDateFormat("dd.MM.yyyy").format(utilDate));
        dto.setReifegradId(lieferung.getReifegradId());
        dto.setErntemonatId(lieferung.getErntemonatId());
        dto.setLagerId(lieferung.getLagerId());
        return dto;
    }

    private ReifegradResourceDTO convertReifegradToReifegradResource (Reifegrad reifegrad) {
        ReifegradResourceDTO dto = new ReifegradResourceDTO();
        dto.setReifegradID(reifegrad.getReifegradID());
        dto.setObstsortenBezeichnung(reifegrad.getObstsortenBezeichnung());
        dto.setMindestlagerdauerInTagen(reifegrad.getMindestlagerdauerInTagen());
        return dto;
    }

    private ErntemonatResourceDTO convertErntemonatToErntemonatResource(Erntemonat erntemonat) {
        ErntemonatResourceDTO dto = new ErntemonatResourceDTO();
        dto.setErntemonatId(erntemonat.getErntemonatId());
        dto.setBezeichnung(erntemonat.getBezeichnung());
        dto.setAnzahlRegentage(erntemonat.getAnzahlRegentage());
        dto.setAnzahlSonnentage(erntemonat.getAnzahlSonnentage());
        dto.setAnbaugebietsBezeichner(erntemonat.getAnbaugebietsBezeichner());
        dto.setFlaeche(erntemonat.getFlaeche());
        dto.setMeereshoehe(erntemonat.getMeereshoehe());
        return dto;
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


