package com.dbiprojekt.lagerhausrest.restData;

import lombok.Data;

@Data
public class ErntemonatResourceDTO {
    private int erntemonatId;
    private String bezeichnung;
    private int anzahlRegentage;
    private int anzahlSonnentage;
    private String anbaugebietsBezeichner;
    private double flaeche;
    private double meereshoehe;
}
