package rabbitmq.example.customer.support.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogisticsRequest {
  private Long customerId;
  private Long ticketId;
}
