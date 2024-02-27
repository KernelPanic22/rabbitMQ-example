package rabbitmq.example.logistics.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import rabbitmq.example.logistics.dto.EventWrapper;
import rabbitmq.example.logistics.dto.LogisticsDto;

@Component
@Slf4j
public class LogisticsListener {

  @RabbitListener(queues = "${spring.rabbitmq.queues.customer-queue.name}")
  public void processLogistics(EventWrapper<?> eventWrapper) {
    log.info("Received message: {}", eventWrapper);
    System.out.println(eventWrapper.getEpochTime());
  }
}
