package br.com.notificacao.recebimento;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class SqsConfiguration {

    private QueueMessagingTemplate messagingTemplate;

    @Value("${cloud.aws.end-point.uri}")
    private String uri;
    @Value("${cloud.aws.region.static}")
    private String region;
    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;
    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Bean
    public QueueMessagingTemplate queueMessagingTemplate(AmazonSQSAsync amazonSQS) {
        return new QueueMessagingTemplate(amazonSQS);
    }

    @Bean
    @Primary
    public AmazonSQSAsync amazonSQS() {
        return AmazonSQSAsyncClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(uri, region))
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials(accessKey, secretKey)
                        )
                ).build();
    }


    /*
    ***
    * Para ter o conhecimento. Estas configuracoes nao foram usadas aqui.
    * A utilidade deste codigo serve apenas para apresentar as configuracoes que podem ser feitas na queue
    * e como impplementar no Spring
    * **
    @Bean
    public SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory(AmazonSQSAsync amazonSQSAsync, AsyncTaskExecutor asyncTaskExecutor) {
        SimpleMessageListenerContainerFactory factory = new SimpleMessageListenerContainerFactory();
        //Define o AmazonSQSAsyncqueue que sera usado pelo container para interagir com a API de mensagens (SQS)
        factory.setAmazonSqs(amazonSQSAsync);

        //Configura o numero maximo de mensagens que deve ser recuperada durante uma sondagem no no sistema Amazon SQS
        factory.setMaxNumberOfMessages(10);

        //O numero de milissegundos que o encadeamento de sondagem deve esperar antes de tentar se recuperar quando ocorrer um erro
        factory.setBackOffTime(100l);
        //Configura a TaskExecutorQueue que serve para sondar mensagens e executa-las chamando os metodos do manipulador.
        factory.setTaskExecutor(asyncTaskExecutor);

        return factory;
    }

    @Bean
    public QueueMessageHandlerFactory queueMessageHandlerFactory(MessageConverter messageConverter) {
        var factory = new QueueMessageHandlerFactory();
        factory.setArgumentResolvers(Collections.singletonList(new PayloadArgumentResolver(messageConverter)));

        return factory;
    }

    @Bean
    public MessageConverter messageConverter(ObjectMapper objectMapper) {
        var converter = new MappingJackson2MessageConverter();

        converter.setObjectMapper(objectMapper);

        converter.setSerializedPayloadClass(String.class);

        converter.setStrictContentTypeMatch(false);
        return converter;
    }*/
}
