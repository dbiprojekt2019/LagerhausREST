package com.dbiprojekt.lagerhausrest.dataServices;

import com.dbiprojekt.lagerhausrest.exceptions.runtimeException.LagerhausBadRequestException;
import com.dbiprojekt.lagerhausrest.manager.LagerhausManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LagerhausDataService {

    @Autowired
    private LagerhausManager manager;

    /*
    private void checkFooValid(FooDTO dto){

        if (foo == null)
            throw new LagerhausBadRequestException("Foo cannot be null")

    }

    private Bar convertFooDTOToBar(FooDTO dto)
    {

    }

    private FooResource convertBarToFooResource(Bar bar)
    {

    }

    public List<FooResource> getFoo(){
        List<Bar> bars = manager.getBar();

        List<FooResource> result = new ArrayList<>();
        for(Bar b : bars){
            result.add(convertBarToFooResource(b));
        }

        return result;
    }
    */

}


