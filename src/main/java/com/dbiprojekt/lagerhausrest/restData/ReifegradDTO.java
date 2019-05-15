package com.dbiprojekt.lagerhausrest.restData;

import lombok.Data;

@Data
public class ReifegradDTO {
    private int reifegradID;
    private String obstsortenBezeichnung;
    private int mindestlagerdauerInTagen;
}
