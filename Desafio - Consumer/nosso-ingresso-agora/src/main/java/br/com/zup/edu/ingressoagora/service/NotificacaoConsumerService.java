package br.com.zup.edu.ingressoagora.service;

import br.com.zup.edu.ingressoagora.model.Cliente;
import br.com.zup.edu.ingressoagora.repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class NotificacaoConsumerService {

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @Autowired
    private ClienteRepository repository;


    private Logger logger = LoggerFactory.getLogger(NotificacaoConsumerService.class);

    @SqsListener(value = "${cloud.aws.queue.name}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void receive(Notificacao notificacao) {
        logger.info("Notificacao - {}", notificacao.toString());

        repository.save(new Cliente(
                notificacao.getNome(), notificacao.getEmail(), LocalDate.parse(notificacao.getNascimento()), notificacao.getCategoria()
        ));

        logger.info("Salvo na base com sucesso");
    }
}
