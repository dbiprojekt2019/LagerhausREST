package com.dbiprojekt.lagerhausrest.exceptions;

public class LagerhausDatabaseConnectionFailed extends Exception {
    public LagerhausDatabaseConnectionFailed(String msg) {
        super(msg);
    }
}
