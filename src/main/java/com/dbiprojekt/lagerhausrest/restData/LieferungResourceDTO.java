package com.dbiprojekt.lagerhausrest.restData;

import lombok.Data;

@Data
public class LieferungResourceDTO {
    private int lieferungsId;
    private double umfangInTonnen;
    private String datumEinlagerung;
    private int reifegradId;
    private int erntemonatId;
    private int lagerId;
}
