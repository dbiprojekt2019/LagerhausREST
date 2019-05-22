package com.dbiprojekt.lagerhausrest.restData;

import lombok.Data;

import java.sql.Date;

//import java.util.Date;

@Data
public class LieferungResourceDTO {
    private int lieferungsId;
    private int umfangInTonnen;
    private String datumEinlagerung;
    private int reifegradId;
    private int erntemonatId;
    private int lagerId;
}
