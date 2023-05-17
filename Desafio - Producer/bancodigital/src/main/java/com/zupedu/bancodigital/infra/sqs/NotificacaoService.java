package com.zupedu.bancodigital.infra.sqs;

import com.google.gson.Gson;
import com.zupedu.bancodigital.infra.sqs.model.Notificacao;
import com.zupedu.bancodigital.model.enums.TipoPagamento;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoService {

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;
    private final Logger logger = LoggerFactory.getLogger(NotificacaoService.class);
    Gson gson = new Gson();

    @Async
    public void send(Double valor, TipoPagamento tipoPagamento, Integer numeroConta, Integer agenciaConta, String origem) {
        Notificacao message = new Notificacao(valor, tipoPagamento.name(), numeroConta, agenciaConta, origem);

        String payload = gson.toJson(message);

        send(payload);

    }


    private void send(String payload) {
        Message<String> notificacao = MessageBuilder.withPayload(payload).build();

        queueMessagingTemplate.convertAndSend("pagamentos_realizados", notificacao);
        logger.info("Mensagem enviada com sucesso");
    }
}
