package com.zupedu.bancodigital.service.sqs;

import com.zupedu.bancodigital.model.Comprovante;
import com.zupedu.bancodigital.repository.ComprovanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComprovanteService {

    @Autowired
    private ComprovanteRepository repository;

    @Autowired
    private SqsProducer sqsProducer;

    public Comprovante salvarComprovante(Comprovante comprovante) {
        var comprovanteSalvo = repository.save(comprovante);

        sqsProducer.send(comprovante);
        return  comprovanteSalvo;
    }

}
