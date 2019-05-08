package com.dbiprojekt.lagerhausrest;

import com.dbiprojekt.lagerhausrest.dataServices.LagerhausDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/fum")
public class LagerhausRESTController {

    @Autowired
    private LagerhausDataService dataService;

    /*
    @RequestMapping(value = "/foo", method = RequestMethod.GET)
    public HttpEntity<List<fooResource>> getFoo(){
        List<fooResource> foo = dataService.getFoo();
        return new HttpEntity<List<fooResource>>(foo);
    }
    */

}
