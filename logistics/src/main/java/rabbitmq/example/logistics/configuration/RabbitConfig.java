package rabbitmq.example.logistics.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "rabbitmq.enabled", matchIfMissing = true)
public class RabbitConfig {

  public static final String AMQ_TOPIC_EXCHANGE = "amq.topic";

  private static final String DEAD_LETTER_EXCHANGE = "deadLetterExchange";

  private static final String CUSTOMER_LOGISTICS_ROUTING_KEY = "customer.logistics";

  //Transform the message to JSON and vice versa
  @Bean
  public Jackson2JsonMessageConverter jackson2JsonMessageConverter(ObjectMapper objectMapper) {
    return new Jackson2JsonMessageConverter(objectMapper);
  }


  @Bean
  public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
      ConnectionFactory connectionFactory, MessageConverter messageConverter) {
    final SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory);
    factory.setMessageConverter(messageConverter);
    return factory;
  }

  @Bean
  TopicExchange exchange() {
    return new TopicExchange(AMQ_TOPIC_EXCHANGE);
  }

  @Bean
  Queue customerQueue(@Value("${spring.rabbitmq.queues.customer-queue.name}") String queueName) {
    return getQueueDeadLetterExchange(queueName);
  }

  @Bean
  Binding customerBinding(Queue customerQueue, TopicExchange exchange) {
    return BindingBuilder.bind(customerQueue).to(exchange).with(CUSTOMER_LOGISTICS_ROUTING_KEY);
  }

  private Queue getQueueDeadLetterExchange(String queueName) {
    return QueueBuilder.durable(queueName).deadLetterExchange(DEAD_LETTER_EXCHANGE).build();
  }
}
