package com.dbiprojekt.lagerhausrest.data;

import lombok.Data;

//import java.util.Date;
import java.sql.Date;

@Data
public class Lieferung {
    private int lieferungsId;
    private double umfangInTonnen;
    private Date datumEinlagerung;
    private int reifegradId;
    private int erntemonatId;
    private int lagerId;
}
