package com.workercompras.repository;

import com.workercompras.model.Endereco;
import org.springframework.stereotype.Component;

@Component
public class HystrixClientFallback implements CepRepository{


    @Override
    public Endereco buscarPorCep(String cep) throws Exception {
        throw new Exception("Cep nao encontrado");
    }
}
