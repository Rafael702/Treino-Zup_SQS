package com.zupedu.livraria.notificacao;

import com.google.gson.Gson;
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

    @Value("${cloud.aws.queue.name}")
    private String queueName;

    Gson gson = new Gson();

    @Async
    public void send(String email, String titulo, String mensagem) {
        Notificacao notificacao = new Notificacao(email, TipoDeNotificacao.EMAIL.name(), titulo, mensagem);

        String payload = gson.toJson(notificacao);
        send(payload);
    }

    private void send(String payload) {

        Message<String> message = MessageBuilder.withPayload(payload).build();

        queueMessagingTemplate.convertAndSend(queueName, message);
    }

}
