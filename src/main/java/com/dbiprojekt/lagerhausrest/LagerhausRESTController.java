package com.dbiprojekt.lagerhausrest;

import com.dbiprojekt.lagerhausrest.dataServices.LagerhausDataService;
import com.dbiprojekt.lagerhausrest.restData.ErntemonatResourceDTO;
import com.dbiprojekt.lagerhausrest.restData.LagerhausResourceDTO;
import com.dbiprojekt.lagerhausrest.restData.LieferungResourceDTO;
import com.dbiprojekt.lagerhausrest.restData.ReifegradResourceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/LagerhausREST")
public class LagerhausRESTController {

    @Autowired
    private LagerhausDataService dataService;

    @RequestMapping(value = "/Lagerhaus", method = RequestMethod.GET)
    public HttpEntity<List<LagerhausResourceDTO>> getAllLagerhausObjects(){
        return new HttpEntity<>(dataService.getAllLagerhausObjects());
    }

    @RequestMapping(value = "/Lagerhaus/{lagerhausId}/Lieferung", method = RequestMethod.GET)
    public HttpEntity<List<LieferungResourceDTO>> getLieferungOfLagerhaus(@PathVariable("lagerhausId") int lagerhausId){
        return new HttpEntity<>(dataService.getLieferungOfLagerhaus(lagerhausId));
    }

    @RequestMapping(value = "/Reifegrad/{reifegradId}", method = RequestMethod.GET)
    public ReifegradResourceDTO getReifegrad(@PathVariable("reifegradId") int reifegradId){
        return dataService.getReifegrad(reifegradId);
    }

    @RequestMapping(value = "/Erntemonat/{erntemonatId}", method = RequestMethod.GET)
    public ErntemonatResourceDTO getErntemonat(@PathVariable("erntemonatId") int erntemonatId){
        return dataService.getErntemonat(erntemonatId);
    }

    @RequestMapping(value = "/Lieferung/{lieferungId}", method = RequestMethod.POST)
    public LieferungResourceDTO insertLieferung(@PathVariable("lieferungId") int lieferungId, @RequestBody LieferungResourceDTO lieferung){
        return dataService.insertLieferung(lieferungId, lieferung);
    }

    @RequestMapping(value = "/Lieferung/{lieferungId}", method = RequestMethod.PUT)
    public LieferungResourceDTO updateLieferung(@PathVariable("lieferungId") int lieferungId, @RequestBody LieferungResourceDTO lieferung){
        return dataService.updateLieferung(lieferungId, lieferung);
    }

    @RequestMapping(value = "/Lieferung/{lieferungId}", method = RequestMethod.DELETE)
    public void deleteLieferung(@PathVariable("lieferungId") int lieferungId){
        dataService.deleteLieferung(lieferungId);
    }

}
