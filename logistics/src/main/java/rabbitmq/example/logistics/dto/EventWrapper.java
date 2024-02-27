package rabbitmq.example.logistics.dto;

import lombok.Builder;
import lombok.Data;
import rabbitmq.example.logistics.enums.EventType;


@Data
@Builder
public class EventWrapper<T> {

  private EventType type;
  private T message;
  private Long epochTime;
}
