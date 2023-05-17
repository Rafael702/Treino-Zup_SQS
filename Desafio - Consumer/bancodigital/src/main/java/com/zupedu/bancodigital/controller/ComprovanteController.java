package com.zupedu.bancodigital.controller;

import com.zupedu.bancodigital.model.Comprovante;
import com.zupedu.bancodigital.service.sqs.ComprovanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comprovante")
public class ComprovanteController {

    @Autowired
    private ComprovanteService service;

    @PostMapping("/cadastro")
    public Comprovante cadastrarComprovante(@RequestBody Comprovante comprovante) {
        return service.salvarComprovante(comprovante);
    }

}