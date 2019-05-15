package com.dbiprojekt.lagerhausrest.data;

import lombok.Data;

@Data
public class Erntemonat {
    private int erntemonatId;
    private String bezeichnung;
    private int anzahlRegentage;
    private int anzahlSonnentage;
    private String anbaugebietsBezeichner;
    private double flaeche;
    private double meereshoehe;
}
