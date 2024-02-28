# RabbitMQ-example

## Descripción
Este proyecto es un ejemplo de como usar RabbitMQ con Spring Boot. 
Se trata de un simple productor y consumidor de mensajes entre dos aplicaciones Spring Boot,
configuradas de una forma sencilla y reutilizable.

## Antes de empezar
Para poder ejecutar este proyecto necesitarás tener instalado RabbitMQ en tu máquina
o dentro de la carpeta base del proyecto correr el siguiente comando:
```bash
docker-compose up
```

## Ejecución
Ejecutar las aplicaciones de customer.suport y logistics,
dentro de la carpeta de cada aplicación ejecutar el siguiente comando:
```bash
mvn spring-boot:run
```

## Uso
Una vez que las aplicaciones estén corriendo, podes hacer las peticiones a traves de swagger:
- [Customer Support](http://localhost:8081/docs)

Una vez que se envie un mensaje, se podrá ver en la consola de customer.support 
que se envió el mensaje y en la consola de logistics que se recibió el mensaje.

