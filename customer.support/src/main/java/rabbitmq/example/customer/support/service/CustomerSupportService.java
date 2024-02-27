package rabbitmq.example.customer.support.service;

import static rabbitmq.example.customer.support.configuration.RabbitConfig.CUSTOMER_LOGISTICS_ROUTING_KEY;
import static rabbitmq.example.customer.support.enums.EventType.CUSTOMER_PROCESSING_LOGISTICS;

import java.time.Clock;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import rabbitmq.example.customer.support.event.PublishEvent;
import rabbitmq.example.customer.support.model.dto.LogisticsDto;
import rabbitmq.example.customer.support.model.dto.LogisticsRequest;

@Service
public class CustomerSupportService {

  Clock clock;

  public CustomerSupportService(Clock clock) {
    this.clock = clock;
  }

  @PublishEvent(eventType = CUSTOMER_PROCESSING_LOGISTICS, routingKey = CUSTOMER_LOGISTICS_ROUTING_KEY)
  public LogisticsDto submitLogistics(LogisticsRequest logisticsRequest) {
    return LogisticsDto.builder().customerId(logisticsRequest.getCustomerId())
        .ticketId(logisticsRequest.getTicketId()).timestamp(LocalDateTime.now(clock)).build();
  }
}
