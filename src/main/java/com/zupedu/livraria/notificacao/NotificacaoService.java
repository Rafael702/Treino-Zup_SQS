package com.zupedu.livraria.notificacao;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

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

        String payload = gson.toJson(notificacao);
        logger.info("Enviando Mensagem Para A Fila: {}", queueName);
        send(payload);
    }

    private void send(String payload) {
        Message<String> message = MessageBuilder.withPayload(payload).build();

        queueMessagingTemplate.convertAndSend(queueName, message);
        logger.info("Mensagem enviada com sucesso");
    }

}
