package com.dbiprojekt.lagerhausrest.data;

import lombok.Data;

@Data
public class Reifegrad {
    private int reifegradID;
    private String obstsortenBezeichnung;
    private int mindestlagerdauerInTagen;
}
