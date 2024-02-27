package rabbitmq.example.customer.support.event;

import java.time.Instant;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import rabbitmq.example.customer.support.configuration.RabbitConfig;
import rabbitmq.example.customer.support.model.dto.EventWrapper;

@Component
@Aspect
@Slf4j
public class PublishEventAspect {

  private final RabbitTemplate rabbitTemplate;

  public PublishEventAspect(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  @AfterReturning(pointcut = "@annotation(publishEvent)", returning = "result")
  public void publishEvent(JoinPoint joinPoint, Object result, PublishEvent publishEvent) {
    var eventType = publishEvent.eventType();
    var routingKey = publishEvent.routingKey();
    log.info("Publish event {} for routing-key {}", eventType, routingKey);
    var event = EventWrapper.builder().type(eventType).message(result)
        .epochTime(Instant.now().getEpochSecond()).build();

    rabbitTemplate.convertAndSend(RabbitConfig.AMQ_TOPIC_EXCHANGE, publishEvent.routingKey(),
        event);
  }
}
