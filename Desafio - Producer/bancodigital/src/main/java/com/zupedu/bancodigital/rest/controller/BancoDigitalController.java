package com.zupedu.bancodigital.rest.controller;

import com.zupedu.bancodigital.infra.sqs.NotificacaoService;
import com.zupedu.bancodigital.model.BancoDigital;
import com.zupedu.bancodigital.repository.BancoDigitalRepository;
import com.zupedu.bancodigital.rest.dto.BancoUpdateDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/bancos")
public class BancoDigitalController {
    @Autowired
    private BancoDigitalRepository repository;

    @Autowired
    private NotificacaoService notificacaoService;

    private Logger logger = LoggerFactory.getLogger(BancoDigitalController.class);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BancoDigital save(@RequestBody BancoDigital bancoDigital) {
        logger.info("Salva banco");
        return repository.save(bancoDigital);
    }

    @PatchMapping("/transfer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BancoDigital transfereMoney(@PathVariable String id, @RequestBody BancoUpdateDTO bancoRequest) {
        logger.info("Transfere valor");

        Optional<BancoDigital> banco = repository.findById(Long.parseLong(id));

        if (banco.get().getValor() >= bancoRequest.getValor()) {
            banco.get().setValor(banco.get().getValor() - bancoRequest.getValor());
            BancoDigital notificacaoTrasnferencia = new BancoDigital(
                    banco.get().getId(),
                    banco.get().getValor(),
                    banco.get().getNumeroConta(),
                    banco.get().getAgenciaConta(),
                    bancoRequest.getOrigem() == null ? banco.get().getOrigem() : bancoRequest.getOrigem(),
                    banco.get().getTipoPagamento());

            notificacaoService.send(
                    notificacaoTrasnferencia.getValor(),
                    notificacaoTrasnferencia.getTipoPagamento(),
                    notificacaoTrasnferencia.getNumeroConta(),
                    notificacaoTrasnferencia.getAgenciaConta(),
                    notificacaoTrasnferencia.getOrigem()
            );
        } else {
            throw new RuntimeException("Valor a ser transferido é maior do que o valor que esta na conta.");
        }
        return repository.save(banco.get());
    }

    @GetMapping("/{id}")
    public BancoDigital extratoDaConta(@PathVariable String id) {
        return repository.findById(Long.parseLong(id)).get();
    }
}