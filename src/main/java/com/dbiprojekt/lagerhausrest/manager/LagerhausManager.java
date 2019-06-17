package com.dbiprojekt.lagerhausrest.manager;

import com.dbiprojekt.lagerhausrest.data.*;
import com.dbiprojekt.lagerhausrest.data.databasePOJOs.*;
import com.dbiprojekt.lagerhausrest.exceptions.LagerhausDatabaseConnectionFailed;
import com.dbiprojekt.lagerhausrest.exceptions.LagerhausDatabaseStatementFailed;
import com.dbiprojekt.lagerhausrest.manager.dbRepositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class LagerhausManager {

    /*
    @Autowired
    DatabaseManager databaseManager;
    */

    @Autowired
    DeliveryRepository deliveryRepository;
    @Autowired
    GrowingAreaRepository growingAreaRepository;
    @Autowired
    HarvestMonthRepository harvestMonthRepository;
    @Autowired
    KindOfFruitRepository kindOfFruitRepository;
    @Autowired
    MaturityLevelRepository maturityLevelRepository;
    @Autowired
    WareHouseRepository wareHouseRepository;



    //<editor-fold desc="deprecated code">
    public List<Lagerhaus> getAllLagerhausObjects() throws LagerhausDatabaseConnectionFailed, LagerhausDatabaseStatementFailed {
        /*
        List<Lagerhaus> lagerhausList = new ArrayList<>();


        List<Map<String, Object>> result = databaseManager.sendQueryToDatabase(
                StoredProcedure.GET_ALL_LAGERHAUS,
                new Object[]{},
                new String[]{
                        databaseManager.collumns.WAREHOUSE_ID,
                        databaseManager.collumns.WAREHOUSE_DESCRIPTION
                    }
                );
        for (Map map : result) {
            Lagerhaus l = new Lagerhaus();
            l.setLagerId((int) map.get(databaseManager.collumns.WAREHOUSE_ID));
            l.setBezeichnung((String) map.get(databaseManager.collumns.WAREHOUSE_DESCRIPTION));
            lagerhausList.add(l);
        }

        return lagerhausList;
         */
        List<Lagerhaus> l = new ArrayList<>();
        Lagerhaus a = new Lagerhaus();
        a.setLagerId(1);
        a.setBezeichnung("Lager 1");
        Lagerhaus b = new Lagerhaus();
        b.setLagerId(2);
        a.setBezeichnung("Lager 2");
        l.add(a);
        l.add(b);
        return l;
    }

    public List<Lieferung> getLieferungOfLagerhaus(int lagerhausId) throws LagerhausDatabaseConnectionFailed, LagerhausDatabaseStatementFailed  {
        /*
        List<Lieferung> lieferungList = new ArrayList<>();

        List<Map<String, Object>> result = databaseManager.sendQueryToDatabase(
                StoredProcedure.GET_LIEFERUNG_BY_ID,
                new Object[]{lagerhausId},
                new String[]{
                        databaseManager.collumns.DELIVERY_ID,
                        databaseManager.collumns.DELIVERY_WEIGHT_TONS,
                        databaseManager.collumns.DELIVERY_DATE,
                        databaseManager.collumns.DELIVERY_MATURITY_FK_ID,
                        databaseManager.collumns.DELIVERY_HARVESTMONTH_FK_ID,
                        databaseManager.collumns.DELIVERY_WAREHOUSE_FK_ID
                }
        );
        for (Map map : result) {
            Lieferung l = new Lieferung();
            l.setLieferungsId((int) map.get(databaseManager.collumns.DELIVERY_ID));
            l.setUmfangInTonnen((int) map.get(databaseManager.collumns.DELIVERY_WEIGHT_TONS));
            l.setDatumEinlagerung((Date) map.get(databaseManager.collumns.DELIVERY_DATE));
            l.setReifegradId((int) map.get(databaseManager.collumns.DELIVERY_MATURITY_FK_ID));
            l.setErntemonatId((int) map.get(databaseManager.collumns.DELIVERY_HARVESTMONTH_FK_ID));
            l.setLagerId((int) map.get(databaseManager.collumns.DELIVERY_WAREHOUSE_FK_ID));
            lieferungList.add(l);
        }

        return lieferungList;
        */
        List<Lieferung> l = new ArrayList<>();
        Lieferung a = new Lieferung();
        a.setLagerId(1);
        a.setErntemonatId(1);
        java.util.Date utilDate = null;
        try {
            utilDate = new SimpleDateFormat("dd.MM.yyyy").parse("01.01.2001");
            a.setDatumEinlagerung(new Date(utilDate.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        a.setUmfangInTonnen(25);
        a.setLieferungsId(1);
        a.setReifegradId(1);
        Lieferung b = new Lieferung();
        b.setLagerId(1);
        b.setErntemonatId(1);
        try {
            utilDate = new SimpleDateFormat("dd.MM.yyyy").parse("02.02.2002");
            b.setDatumEinlagerung(new Date(utilDate.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        b.setUmfangInTonnen(35);
        b.setLieferungsId(2);
        b.setReifegradId(1);
        l.add(a);
        l.add(b);
        return l;
    }

    public Reifegrad getReifegrad(int reifegradId) throws LagerhausDatabaseConnectionFailed, LagerhausDatabaseStatementFailed  {
        /*
        Reifegrad reife = null;

        List<Map<String, Object>> result = databaseManager.sendQueryToDatabase(
                StoredProcedure.GET_REIFEGRAD_BY_ID,
                new Object[]{reifegradId},
                new String[]{
                        databaseManager.collumns.MATURITY_ID,
                        databaseManager.collumns.MATURITY_MIN_STORAGE_TIME,
                        databaseManager.collumns.MATURITY_FRUIT_FK_ID
                }
        );
        for (Map map : result) {
            reife = new Reifegrad();
            reife.setReifegradID((int) map.get(databaseManager.collumns.MATURITY_ID));
            reife.setMindestlagerdauerInTagen((int) map.get(databaseManager.collumns.MATURITY_MIN_STORAGE_TIME));
            reife.setObstsortenBezeichnung((String) map.get(databaseManager.collumns.MATURITY_FRUIT_FK_ID));
            break;
        }

        return reife;
        */

        Reifegrad r = new Reifegrad();
        r.setReifegradID(1);
        r.setMindestlagerdauerInTagen(30);
        r.setObstsortenBezeichnung("Obst");
        return r;
    }

    public Erntemonat getErntemonat(int erntemonatId) {
        Erntemonat e = new Erntemonat();
        e.setErntemonatId(1);
        e.setAnbaugebietsBezeichner("Anbaugebiet A");
        e.setAnzahlRegentage(22);
        e.setAnzahlSonnentage(8);
        e.setBezeichnung("Erntemonat 1");
        e.setFlaeche(2000);
        e.setMeereshoehe(500);
        return e;
    }

    public Lieferung insertLieferung(int lieferungId, Lieferung lieferung) {
        return lieferung;
    }

    public Lieferung updateLieferung(int lieferungId, Lieferung lieferung) {
        return lieferung;
    }

    public void deleteLieferung(int lieferungId) {



    }
    //</editor-fold>


    public List<WareHouse> getAllWareHouseObjects() {

        List<WareHouse> wareHouses = new ArrayList<>();

        for (WareHouse w : wareHouseRepository.findAll()) {
            wareHouses.add(w);
        }

        return wareHouses;
    }

    public List<Delivery> getDeliveriesOfWareHouse(int wareHouseID) {
        List<Delivery> deliveries = new ArrayList<>();

        for(Delivery d : deliveryRepository.findByWareHouseID(wareHouseID)){
            deliveries.add(d);
        }

        return deliveries;
    }

    public FullMaturityLevel getFullMaturityLevel(int maturityID) {
        FullMaturityLevel fullMaturityLevel = new FullMaturityLevel();
        MaturityLevel m = maturityLevelRepository.findOne(maturityID);
        if (m == null) return null;
        fullMaturityLevel.setMaturityLevel(m);
        fullMaturityLevel.setKindOfFruit(kindOfFruitRepository.findOne(m.getFruitID()));
        return fullMaturityLevel;
    }

    public FullHarvestMonth getFullHarvestMonth(int harvestMonthID) {
        FullHarvestMonth fullHarvestMonth = new FullHarvestMonth();
        HarvestMonth h = harvestMonthRepository.findOne(harvestMonthID);
        if (h == null) return null;
        fullHarvestMonth.setHarvestMonth(h);
        fullHarvestMonth.setGrowingArea(growingAreaRepository.findOne(h.getGrowingAreaID()));
        return fullHarvestMonth;
    }

    public Delivery insertDelivery(Delivery delivery) {
        deliveryRepository.save(delivery);
        return delivery;
    }

    public Delivery updateDelivery(int deliveryID, Delivery delivery) {
        Delivery d = deliveryRepository.findOne(deliveryID);
        d.setWeight(delivery.getWeight());
        d.setDateOfDelivery(delivery.getDateOfDelivery());
        d.setMaturityID(delivery.getMaturityID());
        d.setHarvestMonthID(delivery.getHarvestMonthID());
        d.setWareHouseID(delivery.getWareHouseID());
        deliveryRepository.save(d);
        return d;
    }

    public void deleteDelivery(int deliveryID) {
        deliveryRepository.delete(deliveryID);
    }

}
