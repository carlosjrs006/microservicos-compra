package com.worker.validador.service.exception;

public class SaldoInsuficienteException extends RuntimeException{

    public SaldoInsuficienteException(String message){
        super(message);
    }
}
