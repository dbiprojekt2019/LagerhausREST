package com.dbiprojekt.lagerhausrest.manager;

import com.dbiprojekt.lagerhausrest.data.*;
import com.dbiprojekt.lagerhausrest.data.databasePOJOs.*;
import com.dbiprojekt.lagerhausrest.manager.dbRepositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class LagerhausManager {

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
        if (d == null) return null;
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
