package com.dbiprojekt.lagerhausrest.restData;

import lombok.Data;

import java.sql.Date;

//import java.util.Date;

@Data
public class LieferungResource {
    private int lieferungsId;
    private int umfangInTonnen;
    private Date datumEinlagerung;
    private int reifegradId;
    private int erntemonatId;
    private int lagerId;
}
