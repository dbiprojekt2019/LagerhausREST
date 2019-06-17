package com.dbiprojekt.lagerhausrest.dataServices;

import com.dbiprojekt.lagerhausrest.data.*;
import com.dbiprojekt.lagerhausrest.data.databasePOJOs.Delivery;
import com.dbiprojekt.lagerhausrest.data.databasePOJOs.WareHouse;
import com.dbiprojekt.lagerhausrest.exceptions.runtimeException.LagerhausBadRequestException;
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

    //<editor-fold desc="DTO to Object converters">d

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

    //</editor-fold>

    //<editor-fold desc="Object to Resource converters">

    private LagerhausResourceDTO convertWareHouseToLagerhausResource(WareHouse wareHouse){
        LagerhausResourceDTO dto = new LagerhausResourceDTO();
        if (wareHouse == null) return null;
        dto.setLagerId(wareHouse.getWareHouseId());
        dto.setBezeichnung(wareHouse.getDescription());
        return dto;
    }

    private LieferungResourceDTO convertDeliveryToLieferungResource(Delivery delivery){
        LieferungResourceDTO dto = new LieferungResourceDTO();
        if (delivery == null) return null;
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
        if (fullMaturityLevel == null) return null;
        dto.setReifegradID(fullMaturityLevel.getMaturityLevel().getMaturityID());
        dto.setMindestlagerdauerInTagen(fullMaturityLevel.getMaturityLevel().getMinStorageTime());
        dto.setObstsortenBezeichnung(fullMaturityLevel.getKindOfFruit().getDescription());
        return dto;
    }

    private ErntemonatResourceDTO convertFullHarvestMonthToErntemonatResource(FullHarvestMonth fullHarvestMonth) {
        ErntemonatResourceDTO dto = new ErntemonatResourceDTO();
        if (fullHarvestMonth == null) return null;
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

        List<WareHouse> lagerhausList = manager.getAllWareHouseObjects();
        List<LagerhausResourceDTO> result = new ArrayList<>();

        for(WareHouse w : lagerhausList){
            result.add(convertWareHouseToLagerhausResource(w));
        }

        return result;
    }

    public List<LieferungResourceDTO> getLieferungOfLagerhaus(int lagerhausId) {

        List<Delivery> deliveryList = manager.getDeliveriesOfWareHouse(lagerhausId);

        List<LieferungResourceDTO> result = new ArrayList<>();

        for(Delivery d : deliveryList){
            result.add(convertDeliveryToLieferungResource(d));
        }

        return result;
    }

    public ReifegradResourceDTO getReifegrad(int reifeId) {
        return convertFullMaturityLevelToReifegradResource(
                    manager.getFullMaturityLevel(reifeId)
                );
    }

    public ErntemonatResourceDTO getErntemonat(int erntemonatId) {
        return convertFullHarvestMonthToErntemonatResource(
                    manager.getFullHarvestMonth(erntemonatId)
                );
    }

    public LieferungResourceDTO insertLieferung(LieferungResourceDTO lieferung) {
        return convertDeliveryToLieferungResource(
                manager.insertDelivery(convertLieferungDTOToDelivery(lieferung))
        );
    }

    public LieferungResourceDTO updateLieferung(int lieferungId, LieferungResourceDTO lieferung) {
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


