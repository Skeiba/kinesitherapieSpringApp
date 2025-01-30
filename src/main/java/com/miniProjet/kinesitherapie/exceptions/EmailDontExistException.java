package com.miniProjet.kinesitherapie.exceptions;

public class EmailDontExistException extends RuntimeException {
    public EmailDontExistException(String message) {
        super(message);
    }
}
