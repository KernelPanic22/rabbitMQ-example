package rabbitmq.example.customer.support.event;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import rabbitmq.example.customer.support.enums.EventType;

// documentacion de javaDoc
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PublishEvent {

  EventType eventType();

  String routingKey();
}


