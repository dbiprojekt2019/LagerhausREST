package com.dbiprojekt.lagerhausrest.exceptions;

public class LagerhausDatabaseStatementFailed extends Throwable {
    public LagerhausDatabaseStatementFailed(String msg) {
        super(msg);
    }
}
