# CreatorDeal Microservices

This project splits the original CreatorDeal CRM app into five simple Spring Boot microservices without Config Server, Eureka, or an API Gateway.

## Services

| Service              | Port | Database        |
| -------------------- | ---: | --------------- |
| creator-service      | 8081 | creator_db      |
| brand-service        | 8082 | brand_db        |
| deal-service         | 8083 | deal_db         |
| invoice-service      | 8084 | invoice_db      |
| notification-service | 8085 | notification_db |

## Infrastructure

Start PostgreSQL and RabbitMQ:

```bash
docker compose up -d
```

RabbitMQ management UI:

```text
http://localhost:15672
guest / guest
```

PostgreSQL credentials used by the services:

```text
postgres / your_password
```

## Build

```bash
mvn -DskipTests package
```

## Run Services

Open separate terminals and run:

```bash
mvn -pl creator-service spring-boot:run
mvn -pl brand-service spring-boot:run
mvn -pl deal-service spring-boot:run
mvn -pl invoice-service spring-boot:run
mvn -pl notification-service spring-boot:run
```

## Success Flow

Create resources directly through each service:

```text
POST http://localhost:8081/api/creators
POST http://localhost:8082/api/brands
POST http://localhost:8083/api/deals
POST http://localhost:8083/api/deliverables
POST http://localhost:8084/api/invoices
GET  http://localhost:8085/api/reminders/creator/{creatorId}
```

`deal-service` publishes deal and deliverable events. `invoice-service` publishes invoice events. `notification-service` consumes those events and creates reminders automatically when a due date is present.
