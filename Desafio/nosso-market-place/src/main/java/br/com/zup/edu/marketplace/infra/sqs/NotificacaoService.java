package br.com.zup.edu.marketplace.infra.sqs;

import br.com.zup.edu.marketplace.infra.sqs.dto.Notificacao;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoService {

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    private final Logger logger = LoggerFactory.getLogger(NotificacaoService.class);

    private final Gson gson = new Gson();

    public void send(String titulo, String mensagem) {
        Notificacao notificacao = new Notificacao(titulo, mensagem);

        String payload = gson.toJson(notificacao);

        send(payload);
    }

    private void send(String payload) {
        Message<String> notificacao = MessageBuilder.withPayload(payload).build();

        queueMessagingTemplate.convertAndSend("promocao", notificacao);
        logger.info("Mensagem enviada!");
    }

}
