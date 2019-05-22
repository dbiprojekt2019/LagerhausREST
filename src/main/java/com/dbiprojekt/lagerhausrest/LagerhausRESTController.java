package com.dbiprojekt.lagerhausrest;

import com.dbiprojekt.lagerhausrest.dataServices.LagerhausDataService;
import com.dbiprojekt.lagerhausrest.restData.LagerhausResourceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/fum")
public class LagerhausRESTController {

    @Autowired
    private LagerhausDataService dataService;

    @RequestMapping(value = "/GetAllLagerhaeuser", method = RequestMethod.GET)
    public HttpEntity<List<LagerhausResourceDTO>> getAllLagerhausObjects(){
        List<LagerhausResourceDTO> foo = dataService.getAllLagerhausObjects();
        return new HttpEntity<List<LagerhausResourceDTO>>(foo);
    }

}
