package com.zupedu.bancodigital.service.sqs;

import com.zupedu.bancodigital.model.Comprovante;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.http.MediaType;
import org.springframework.messaging.MessageHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SqsProducer {

    @Value("${cloud.aws.queue.name}")
    private String queue;
    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    private Logger logger = LoggerFactory.getLogger(SqsProducer.class);

    @Async
    public void send(Comprovante comprovante){
        Notificacao notificacao = new Notificacao("SMS",comprovante.getData().toString(), comprovante.getDescricao());

        logger.info("Enviando comprovante {}", notificacao);

        send(notificacao);
    }

    private void send(Notificacao notificacao) {

        Map<String, Object> headers = new HashMap<>();
        headers.put(MessageHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        queueMessagingTemplate.convertAndSend(queue, notificacao, headers);
        logger.info("Comprovante enviado com sucesso {}", notificacao);
    }
}
