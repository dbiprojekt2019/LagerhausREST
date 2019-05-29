package com.dbiprojekt.lagerhausrest;

import com.dbiprojekt.lagerhausrest.data.Reifegrad;
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
@RequestMapping("/fum")
public class LagerhausRESTController {

    @Autowired
    private LagerhausDataService dataService;

    @RequestMapping(value = "/Lagerhaus", method = RequestMethod.GET)
    public HttpEntity<List<LagerhausResourceDTO>> getAllLagerhausObjects(){
        List<LagerhausResourceDTO> foo = dataService.getAllLagerhausObjects();
        return new HttpEntity<List<LagerhausResourceDTO>>(foo);
    }

    @RequestMapping(value = "/Lagerhaus/{lagerhausId}/Lieferung", method = RequestMethod.GET)
    public HttpEntity<List<LieferungResourceDTO>> getLieferungOfLagerhaus(@PathVariable("lagerhausId") int lagerhausId){
        List<LieferungResourceDTO> foo = dataService.getLieferungOfLagerhaus(lagerhausId);
        return new HttpEntity<List<LieferungResourceDTO>>(foo);
    }

    @RequestMapping(value = "/Reifegrad/{reifegradId}", method = RequestMethod.GET)
    public ReifegradResourceDTO getReifegrad(@PathVariable("reifegradId") int reifegradId){
        ReifegradResourceDTO r = dataService.getReifegrad(reifegradId);
        return r;
    }

    @RequestMapping(value = "/Erntemonat/{erntemonatId}", method = RequestMethod.GET)
    public ErntemonatResourceDTO getErntemonat(@PathVariable("erntemonatId") int erntemonatId){
        ErntemonatResourceDTO e = dataService.getErntemonat(erntemonatId);
        return e;
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
