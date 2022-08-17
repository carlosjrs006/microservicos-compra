package com.worker.validador.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {

    @Autowired
    private  JavaMailSender javaMailSender;


    public void notificarClienteCompraComSucesso(String email){
        var msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject("Compra confirmada");
        msg.setText("Sua compra foi aprovada! Breve voce recebera seu codigo de rastreio!");
        javaMailSender.send(msg);
        log.info("Cliente notificado da compra aprovadda com sucesso!!");
    }

    public void notificarClienteCompraComError(String email){
        var msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject("Compra reprovada");
        msg.setText("Sua compra foi reprovada! Saldo insuficiente!");
        javaMailSender.send(msg);
        log.info("Cliente notificado da compra com saldo insuficiente!!");
    }
}
