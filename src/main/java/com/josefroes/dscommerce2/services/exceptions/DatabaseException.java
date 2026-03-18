package com.josefroes.dscommerce2.services.exceptions;

public class DatabaseException extends RuntimeException {
    public DatabaseException (String msg){
        super(msg);
    }
}
