package rabbitmq.example.logistics.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogisticsDto {

  private Long customerId;
  private Long ticketId;
  private LocalDateTime timestamp;

}
