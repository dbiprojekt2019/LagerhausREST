package com.dbiprojekt.lagerhausrest.dataServices;

import com.dbiprojekt.lagerhausrest.data.*;
import com.dbiprojekt.lagerhausrest.data.databasePOJOs.Delivery;
import com.dbiprojekt.lagerhausrest.data.databasePOJOs.WareHouse;
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

    private Delivery convertLieferungDTOToDelivery(LieferungResourceDTO dto)
    {
        Delivery d = new Delivery();
        d.setDeliveryID(dto.getLieferungsId());
        d.setWeight(dto.getUmfangInTonnen());
        try {
            d.setDateOfDelivery(new SimpleDateFormat("dd.MM.yyyy").parse(dto.getDatumEinlagerung()));
        } catch (ParseException e) {
            throw new LagerhausBadRequestException("DatumEinlagerung is invalid");
        }
        d.setMaturityID(dto.getReifegradId());
        d.setHarvestMonthID(dto.getErntemonatId());
        d.setWareHouseID(dto.getLagerId());
        return d;
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

    private LagerhausResourceDTO convertWareHouseToLagerhausResource(WareHouse wareHouse){
        LagerhausResourceDTO dto = new LagerhausResourceDTO();
        dto.setLagerId(wareHouse.getWareHouseId());
        dto.setBezeichnung(wareHouse.getDescription());
        return dto;
    }

    private LieferungResourceDTO convertDeliveryToLieferungResource(Delivery delivery){
        LieferungResourceDTO dto = new LieferungResourceDTO();
        dto.setLieferungsId(delivery.getDeliveryID());
        dto.setUmfangInTonnen(delivery.getWeight());
        dto.setDatumEinlagerung(new SimpleDateFormat("dd.MM.yyyy").format(delivery.getDateOfDelivery()));
        dto.setReifegradId(delivery.getMaturityID());
        dto.setErntemonatId(delivery.getHarvestMonthID());
        dto.setLagerId(delivery.getWareHouseID());
        return dto;
    }

    private ReifegradResourceDTO convertFullMaturityLevelToReifegradResource (FullMaturityLevel fullMaturityLevel) {
        ReifegradResourceDTO dto = new ReifegradResourceDTO();
        dto.setReifegradID(fullMaturityLevel.getMaturityLevel().getMaturityID());
        dto.setMindestlagerdauerInTagen(fullMaturityLevel.getMaturityLevel().getMinStorageTime());
        dto.setObstsortenBezeichnung(fullMaturityLevel.getKindOfFruit().getDescription());
        return dto;
    }

    private ErntemonatResourceDTO convertFullHarvestMonthToErntemonatResource(FullHarvestMonth fullHarvestMonth) {
        ErntemonatResourceDTO dto = new ErntemonatResourceDTO();
        dto.setErntemonatId(fullHarvestMonth.getHarvestMonth().getHarvestMonthID());
        dto.setBezeichnung(fullHarvestMonth.getHarvestMonth().getDescription());
        dto.setAnzahlRegentage(fullHarvestMonth.getHarvestMonth().getNumberRainyDays());
        dto.setAnzahlSonnentage(fullHarvestMonth.getHarvestMonth().getNumberSunnyDays());
        dto.setAnbaugebietsBezeichner(fullHarvestMonth.getGrowingArea().getDescription());
        dto.setFlaeche(fullHarvestMonth.getGrowingArea().getArea());
        dto.setMeereshoehe(fullHarvestMonth.getGrowingArea().getSeaLevel());
        return dto;
    }

    //</editor-fold>


    public List<LagerhausResourceDTO> getAllLagerhausObjects() {

        /*
        List<WareHouse> lagerhausList = null;
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
         */

        List<WareHouse> lagerhausList = manager.getAllWareHouseObjects();
        List<LagerhausResourceDTO> result = new ArrayList<>();

        for(WareHouse w : lagerhausList){
            result.add(convertWareHouseToLagerhausResource(w));
        }

        return result;
    }

    public List<LieferungResourceDTO> getLieferungOfLagerhaus(int lagerhausId) {
        /*
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
        */

        List<Delivery> deliveryList = manager.getDeliveriesOfWareHouse(lagerhausId);

        List<LieferungResourceDTO> result = new ArrayList<>();

        for(Delivery d : deliveryList){
            result.add(convertDeliveryToLieferungResource(d));
        }

        return result;
    }

    public ReifegradResourceDTO getReifegrad(int reifeId) {
        /*
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
                 */

        return convertFullMaturityLevelToReifegradResource(
                    manager.getFullMaturityLevel(reifeId)
                );
    }

    public ErntemonatResourceDTO getErntemonat(int erntemonatId) {

        /*
        Erntemonat monat = null;


        try {
            monat = manager.getErntemonat(erntemonatId);
        } catch (LagerhausDatabaseConnectionFailed lagerhausDatabaseConnectionFailed) {
            lagerhausDatabaseConnectionFailed.printStackTrace();
            throw new LagerhausDatabaseException("Database connection failed");
        } catch (LagerhausDatabaseStatementFailed lagerhausDatabaseStatementFailed) {
            lagerhausDatabaseStatementFailed.printStackTrace();
            throw new LagerhausDatabaseException("Request to database failed");
        }

        monat = manager.getErntemonat(erntemonatId);

        return convertErntemonatToErntemonatResource(monat);
                */

        return convertFullHarvestMonthToErntemonatResource(
                    manager.getFullHarvestMonth(erntemonatId)
                );
    }

    public LieferungResourceDTO insertLieferung(LieferungResourceDTO lieferung) {

                /*
        Lieferung result = null;

        try {
            result = manager.insertLieferung(lieferungId, convertLieferungDTOToLieferung(lieferung));
        } catch (LagerhausDatabaseConnectionFailed lagerhausDatabaseConnectionFailed) {
            lagerhausDatabaseConnectionFailed.printStackTrace();
            throw new LagerhausDatabaseException("Database connection failed");
        } catch (LagerhausDatabaseStatementFailed lagerhausDatabaseStatementFailed) {
            lagerhausDatabaseStatementFailed.printStackTrace();
            throw new LagerhausDatabaseException("Request to database failed");
        }

        result = manager.insertLieferung(lieferungId, convertLieferungDTOToLieferung(lieferung));

        return convertLieferungToLieferungResource(result);
                */

        return convertDeliveryToLieferungResource(
                manager.insertDelivery(convertLieferungDTOToDelivery(lieferung))
        );
    }

    public LieferungResourceDTO updateLieferung(int lieferungId, LieferungResourceDTO lieferung) {
        /*
        Lieferung result = null;

        try {
            result = manager.updateLieferung(lieferungId, convertLieferungDTOToLieferung(lieferung));
        } catch (LagerhausDatabaseConnectionFailed lagerhausDatabaseConnectionFailed) {
            lagerhausDatabaseConnectionFailed.printStackTrace();
            throw new LagerhausDatabaseException("Database connection failed");
        } catch (LagerhausDatabaseStatementFailed lagerhausDatabaseStatementFailed) {
            lagerhausDatabaseStatementFailed.printStackTrace();
            throw new LagerhausDatabaseException("Request to database failed");
        }

        result = manager.updateLieferung(lieferungId, convertLieferungDTOToLieferung(lieferung));

        return convertLieferungToLieferungResource(result);
            */

        return convertDeliveryToLieferungResource(
                manager.updateDelivery(
                        lieferungId,
                        convertLieferungDTOToDelivery(lieferung)
                ));
    }

    public void deleteLieferung(int lieferungId) {
        manager.deleteDelivery(lieferungId);
    }
}


