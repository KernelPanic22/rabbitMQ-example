package rabbitmq.example.customer.support.model.dto;

import lombok.Builder;
import lombok.Data;
import rabbitmq.example.customer.support.enums.EventType;

@Data
@Builder
public class EventWrapper<T> {

  private EventType type;
  private T message;
  private Long epochTime;
}
