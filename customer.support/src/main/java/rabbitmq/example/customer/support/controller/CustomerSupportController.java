package rabbitmq.example.customer.support.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rabbitmq.example.customer.support.model.dto.LogisticsRequest;
import rabbitmq.example.customer.support.service.CustomerSupportService;

@RestController
@RequestMapping("/v1/customer-support")
@Tag(name = "Customer Support", description = "It exposes the customer support operations.")
@Slf4j
public class CustomerSupportController {

  private final CustomerSupportService customerSupportService;

  public CustomerSupportController(CustomerSupportService customerSupportService) {
    this.customerSupportService = customerSupportService;
  }

  @PostMapping("/logistics")
  public void submitLogistics(@RequestBody LogisticsRequest logisticsRequest) {
    log.info("Submit logistics");
    customerSupportService.submitLogistics(logisticsRequest);
  }
}
