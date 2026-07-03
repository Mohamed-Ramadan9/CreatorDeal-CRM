# Codex Task: Split CreatorDeal CRM into Simple Microservices First

## Goal

Refactor the current CreatorDeal CRM Spring Boot application into simple independent microservices **without** using these infrastructure services yet:

* Config Server
* Eureka Server
* API Gateway

We want to understand basic microservices first:

* Independent Spring Boot applications
* Independent databases
* Clear service ownership
* No cross-service JPA relationships
* Event-driven communication using RabbitMQ

---

# Current Monolith

Current app contains:

* Creator CRUD
* Brand CRUD
* Deal CRUD
* Deliverable CRUD
* Invoice CRUD
* Reminder CRUD
* Reminder Scheduler
* Auto reminder creation

---

# Target Project Structure

Create:

```text
creator-deal-saas/
│
├── creator-service/
├── brand-service/
├── deal-service/
├── invoice-service/
└── notification-service/
```

Each service must be a separate Spring Boot app with:

```text
pom.xml
src/main/java
src/main/resources/application.yml
```

---

# Ports

```text
creator-service       8081
brand-service         8082
deal-service          8083
invoice-service       8084
notification-service  8085
```

---

# Databases

Use separate PostgreSQL databases:

```text
creator_db
brand_db
deal_db
invoice_db
notification_db
```

Each service connects only to its own DB.

---

# Services

## creator-service

Owns:

```text
Creator
```

Endpoints:

```text
POST   /api/creators
GET    /api/creators
GET    /api/creators/{id}
GET    /api/creators/user/{userId}
PUT    /api/creators/{id}
DELETE /api/creators/{id}
```

---

## brand-service

Owns:

```text
Brand
```

Brand stores:

```java
private Long creatorId;
```

No JPA relation to Creator.

Endpoints:

```text
POST   /api/brands
GET    /api/brands/{id}
GET    /api/brands/creator/{creatorId}
PUT    /api/brands/{id}
DELETE /api/brands/{id}
```

---

## deal-service

Owns:

```text
Deal
DealDeliverable
```

Deal stores:

```java
private Long creatorId;
private Long brandId;
```

No JPA relation to Creator or Brand.

Deliverable may have JPA relation to Deal because both are inside deal-service.

Endpoints:

```text
POST   /api/deals
GET    /api/deals/{id}
GET    /api/deals/creator/{creatorId}
PUT    /api/deals/{id}
DELETE /api/deals/{id}

POST   /api/deliverables
GET    /api/deliverables/{id}
GET    /api/deliverables/deal/{dealId}
GET    /api/deliverables/deal/{dealId}/status/{status}
PUT    /api/deliverables/{id}
DELETE /api/deliverables/{id}
```

---

## invoice-service

Owns:

```text
Invoice
```

Invoice stores:

```java
private Long dealId;
private Long creatorId;
```

No JPA relation to Deal or Creator.

Endpoints:

```text
POST   /api/invoices
GET    /api/invoices/{id}
GET    /api/invoices/deal/{dealId}
GET    /api/invoices/creator/{creatorId}
GET    /api/invoices/creator/{creatorId}/status/{status}
PUT    /api/invoices/{id}
PATCH  /api/invoices/{id}/mark-paid
DELETE /api/invoices/{id}
```

---

## notification-service

Owns:

```text
Reminder
```

Reminder stores:

```java
private Long creatorId;
private Long dealId;
private Long invoiceId;
```

No JPA relation to other services.

Endpoints:

```text
POST   /api/reminders
GET    /api/reminders/{id}
GET    /api/reminders/creator/{creatorId}
GET    /api/reminders/creator/{creatorId}/status/{status}
PUT    /api/reminders/{id}
PATCH  /api/reminders/{id}/cancel
POST   /api/reminders/process-due
DELETE /api/reminders/{id}
```

---

# Event-Driven Communication

Use **RabbitMQ first**.

Do not use Kafka yet.

Why RabbitMQ first:

* Easier to understand
* Good for command/event style communication
* Matches this project well
* Kafka can be added later after RabbitMQ is clear

---

# RabbitMQ Requirement

Use Spring Cloud Stream with RabbitMQ.

Dependencies needed in publishing/consuming services:

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-stream-rabbit</artifactId>
</dependency>
```

---

# Events

Create event records/classes.

## DealCreatedEvent

Published by:

```text
deal-service
```

Consumed by:

```text
notification-service
```

```java
public record DealCreatedEvent(
        Long dealId,
        Long creatorId,
        LocalDate dueDate,
        String title
) {}
```

---

## DeliverableCreatedEvent

Published by:

```text
deal-service
```

Consumed by:

```text
notification-service
```

```java
public record DeliverableCreatedEvent(
        Long deliverableId,
        Long dealId,
        Long creatorId,
        LocalDate dueDate,
        String type
) {}
```

---

## InvoiceCreatedEvent

Published by:

```text
invoice-service
```

Consumed by:

```text
notification-service
```

```java
public record InvoiceCreatedEvent(
        Long invoiceId,
        Long dealId,
        Long creatorId,
        LocalDate dueDate,
        String invoiceNumber
) {}
```

---

# Publishing Rules

## In deal-service

When a deal is created:

1. Save deal in `deal_db`
2. Publish `DealCreatedEvent`

When a deliverable is created:

1. Save deliverable in `deal_db`
2. Publish `DeliverableCreatedEvent`

Use:

```java
StreamBridge
```

Example:

```java
streamBridge.send("dealCreated-out-0", event);
```

---

## In invoice-service

When invoice is created:

1. Save invoice in `invoice_db`
2. Publish `InvoiceCreatedEvent`

Use:

```java
streamBridge.send("invoiceCreated-out-0", event);
```

---

# Consuming Rules

## In notification-service

Create consumers:

```java
@Bean
public Consumer<DealCreatedEvent> dealCreated() { ... }

@Bean
public Consumer<DeliverableCreatedEvent> deliverableCreated() { ... }

@Bean
public Consumer<InvoiceCreatedEvent> invoiceCreated() { ... }
```

When event is consumed, create reminder automatically.

---

# Reminder Creation Rules

## DealCreatedEvent

If `dueDate != null`:

```text
creatorId = event.creatorId
dealId = event.dealId
invoiceId = null
reminderType = DEAL_DEADLINE
scheduledAt = dueDate at 09:00
status = PENDING
```

## DeliverableCreatedEvent

If `dueDate != null`:

```text
creatorId = event.creatorId
dealId = event.dealId
invoiceId = null
reminderType = DELIVERABLE_DUE
scheduledAt = dueDate at 09:00
status = PENDING
```

## InvoiceCreatedEvent

If `dueDate != null`:

```text
creatorId = event.creatorId
dealId = event.dealId
invoiceId = event.invoiceId
reminderType = INVOICE_DUE
scheduledAt = dueDate at 09:00
status = PENDING
```

---

# RabbitMQ Configuration

Create consistent bindings.

## deal-service application.yml

```yaml
spring:
  application:
    name: deal-service

  cloud:
    stream:
      bindings:
        dealCreated-out-0:
          destination: deal-created
        deliverableCreated-out-0:
          destination: deliverable-created
      rabbit:
        binder:
          brokers: localhost
          defaultBrokerPort: 5672
```

## invoice-service application.yml

```yaml
spring:
  application:
    name: invoice-service

  cloud:
    stream:
      bindings:
        invoiceCreated-out-0:
          destination: invoice-created
      rabbit:
        binder:
          brokers: localhost
          defaultBrokerPort: 5672
```

## notification-service application.yml

```yaml
spring:
  application:
    name: notification-service

  cloud:
    function:
      definition: dealCreated;deliverableCreated;invoiceCreated

    stream:
      bindings:
        dealCreated-in-0:
          destination: deal-created
          group: notification-service
        deliverableCreated-in-0:
          destination: deliverable-created
          group: notification-service
        invoiceCreated-in-0:
          destination: invoice-created
          group: notification-service

      rabbit:
        binder:
          brokers: localhost
          defaultBrokerPort: 5672
```

---

# Important Refactor Rules

Remove cross-service JPA relations.

Do not use:

```java
@ManyToOne
private Creator creator;
```

Use:

```java
@Column(name = "creator_id", nullable = false)
private Long creatorId;
```

Do not use:

```java
@ManyToOne
private Brand brand;
```

Use:

```java
@Column(name = "brand_id", nullable = false)
private Long brandId;
```

Do not use JPA joins across services.

---

# DTO Rule

For now, return IDs only.

Example:

```java
public class DealResponse {
    private Long id;
    private Long creatorId;
    private Long brandId;
    private String title;
    private String description;
    private DealStatus status;
    private BigDecimal amount;
    private String currency;
    private LocalDate startDate;
    private LocalDate dueDate;
    private LocalDateTime createdAt;
}
```

Do not return:

```java
creatorName
brandName
```

That can be added later using HTTP clients or API composition.

---

# Docker Compose

Create `docker-compose.yml` with:

```text
PostgreSQL
RabbitMQ
```

Postgres should expose:

```text
5432
```

RabbitMQ should expose:

```text
5672
15672
```

RabbitMQ management UI:

```text
http://localhost:15672
```

Default credentials:

```text
guest / guest
```

---

# What Not To Implement Yet

Do not implement:

* Config Server
* Eureka Server
* API Gateway
* OAuth2
* Keycloak
* Kafka
* Resilience4j
* Observability
* AI Service
* Frontend

These are later phases.

---

# Success Flow Without Gateway

Call services directly.

## 1. Create Creator

```http
POST http://localhost:8081/api/creators
```

## 2. Create Brand

```http
POST http://localhost:8082/api/brands
```

## 3. Create Deal

```http
POST http://localhost:8083/api/deals
```

Expected:

```text
Deal saved in deal_db
DealCreatedEvent published to RabbitMQ
notification-service consumes event
Reminder created in notification_db
```

## 4. Create Deliverable

```http
POST http://localhost:8083/api/deliverables
```

Expected:

```text
Deliverable saved in deal_db
DeliverableCreatedEvent published to RabbitMQ
notification-service consumes event
Reminder created in notification_db
```

## 5. Create Invoice

```http
POST http://localhost:8084/api/invoices
```

Expected:

```text
Invoice saved in invoice_db
InvoiceCreatedEvent published to RabbitMQ
notification-service consumes event
Reminder created in notification_db
```

## 6. Check Reminders

```http
GET http://localhost:8085/api/reminders/creator/{creatorId}
```

Expected:

```text
Auto-created reminders appear
```

---

# Required Deliverables

Generate/refactor:

1. `creator-service``
2. `brand-service`
3. `deal-service`
4. `invoice-service`
5. `notification-service`
6. Separate PostgreSQL DB config per service
7. RabbitMQ event publishing
8. RabbitMQ event consuming
9. Reminder creation from events
10. Reminder scheduler
11. Docker Compose for Postgres + RabbitMQ
12. README with run instructions

---

# Coding Style

Use packages:

```text
com.creatordeal.creatorservice
com.creatordeal.brandservice
com.creatordeal.dealservice
com.creatordeal.invoiceservice
com.creatordeal.notificationservice
```

Use layered structure:

```text
controller
dto
entity
enums
exception
mapper
repository
service
service.impl
event
```

Use Lombok and constructor injection.

Use Jakarta Validation.

Use consistent global error handling.

---

# Final Goal

After this task, the project should be understandable as **simple microservices without infrastructure complexity**.

The main learning goals are:

* Each service is an independent Spring Boot app
* Each service owns its database
* Services do not share JPA entities
* Services communicate through RabbitMQ events
* Notification service reacts to business events and creates reminders
