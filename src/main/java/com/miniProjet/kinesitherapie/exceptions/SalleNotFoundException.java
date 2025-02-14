package com.miniProjet.kinesitherapie.exceptions;

public class SalleNotFoundException extends RuntimeException {
    public SalleNotFoundException(String message) {
        super(message);
    }
}