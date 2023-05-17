package br.com.consumer.infra.sqs.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoListener {

    private Logger logger = LoggerFactory.getLogger(NotificacaoListener.class);

    @SqsListener(value = "${cloud.aws.queue.name}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void received(Notificacao notificacao, @Header("MessageId") String messageId) {
        logger.info("Notificacao recebida, {}, payload {}", messageId, notificacao);
    }
}
