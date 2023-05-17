package com.zupedu.livraria.notificacao;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.http.MediaType;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class NotificacaoService {
    @Autowired
    QueueMessagingTemplate queueMessagingTemplate;

    private final Logger logger = LoggerFactory.getLogger(NotificacaoService.class);

    @Value("${cloud.aws.queue.name}")
    private String queueName;

    Gson gson = new Gson();

    @Async
    public void send(String email, String titulo, String mensagem) {
        Notificacao notificacao = new Notificacao(email, TipoDeNotificacao.EMAIL.name(), titulo, mensagem);

        logger.info("Enviando Mensagem Para A Fila: {}", queueName);
        send(notificacao);
    }

    private void send(Notificacao notificacao) {
//        Message<Notificacao> message = MessageBuilder.withPayload(notificacao)
//                .setHeader(MessageHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();

        Map<String, Object> headers = new HashMap<>();
        headers.put(MessageHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        queueMessagingTemplate.convertAndSend(queueName, notificacao, headers);
        logger.info("Mensagem enviada: {}", notificacao.toString());


        Message<?> message = queueMessagingTemplate.receive(queueName);

        logger.info("Recebendo a mensagem: {}", message.getPayload());
    }

}
