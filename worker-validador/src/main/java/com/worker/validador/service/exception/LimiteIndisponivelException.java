package com.worker.validador.service.exception;

public class LimiteIndisponivelException extends RuntimeException {

    public LimiteIndisponivelException(String message){
        super(message);
    }
}
