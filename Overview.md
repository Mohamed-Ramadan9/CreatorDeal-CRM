# CreatorDeal CRM

## Overview

CreatorDeal CRM is a SaaS platform designed for micro-influencers and content creators to manage sponsorship deals, brand collaborations, deliverables, invoices, reminders, and payments in one place.

Many creators currently manage sponsorships using:

* Instagram DMs
* Gmail
* WhatsApp
* Notes applications
* Excel sheets
* Notion

As creators grow, this process becomes difficult to manage and often leads to:

* Missed deadlines
* Lost contracts
* Forgotten invoices
* Late payments
* Poor organization

CreatorDeal CRM aims to solve these problems through a centralized platform.

---

# Business Domain

## Target Customers

* Micro Influencers (5K - 100K followers)
* Content Creators
* Freelance Creators
* Influencer Managers
* Small Talent Agencies

---

## Main Problems We Solve

### Deal Tracking

Creators need a way to track:

* New opportunities
* Negotiations
* Active campaigns
* Completed campaigns

---

### Deliverable Management

Creators often have obligations such as:

* Instagram Posts
* Instagram Stories
* Reels
* TikTok Videos
* YouTube Videos

The platform tracks:

* Due dates
* Approval status
* Publication status

---

### Invoice Tracking

Creators need to know:

* Which invoices were sent
* Which invoices are overdue
* Which brands have paid
* Which brands still owe money

---

### Reminders

Automatic reminders for:

* Upcoming deliverables
* Campaign deadlines
* Invoice due dates
* Payment follow-ups

---

### Future AI Features

The platform will later include AI capabilities:

#### Contract Analyzer

Upload a contract and automatically extract:

* Payment amount
* Deliverables
* Deadlines
* Exclusivity clauses

#### Deal Assistant

AI helps creators plan campaigns and deliverables.

#### Email Generator

Generate:

* Payment follow-ups
* Negotiation emails
* Brand outreach messages

---

# Technical Goal

This project is not only a SaaS product.

It is also a learning project to practice enterprise-level Java microservices architecture.

The implementation follows concepts from the EasyBytes Spring Boot Microservices course.

---

# Planned Architecture

## Infrastructure Services

### Config Server

Responsibilities:

* Centralized configuration
* Environment-specific properties
* Externalized configuration management

---

### Eureka Server

Responsibilities:

* Service registration
* Service discovery

---

### API Gateway

Responsibilities:

* Routing
* Authentication
* Rate limiting
* Logging
* Cross-cutting concerns

---

## Business Services

### Auth Service

Responsibilities:

* Authentication
* Authorization
* JWT tokens
* OAuth2
* OpenID Connect

---

### Creator Service

Responsibilities:

* Creator profiles
* Social accounts
* Creator settings

---

### Brand Service

Responsibilities:

* Brand management
* Contact information
* Brand history

---

### Deal Service

Responsibilities:

* Sponsorship deals
* Deal lifecycle
* Campaign tracking

---

### Invoice Service

Responsibilities:

* Invoice management
* Payment tracking

---

### Notification Service

Responsibilities:

* Email notifications
* Reminder scheduling
* Future push notifications

---

### AI Service

Responsibilities:

* Contract analysis
* Content generation
* Deal insights

---

# Event-Driven Architecture

Future communication between services will use:

* RabbitMQ
* Kafka
* Spring Cloud Stream

Example:

Deal Created
→ Publish Event
→ Notification Service
→ Create Reminder
→ Send Email

---

# Observability Stack

The project will implement:

* Prometheus
* Grafana
* Loki
* Promtail
* Tempo

Monitoring includes:

* Request metrics
* Error rates
* Service health
* Distributed tracing

---

# Security

The platform will use:

* Spring Security
* OAuth2
* OpenID Connect
* JWT Authentication

Future plan:

* Keycloak integration

---

# Current Database Model

Current entities:

## Creator

Represents a content creator.

## Brand

Represents a company or brand working with creators.

## Deal

Represents a sponsorship agreement.

## Deal Deliverable

Represents required content.

Examples:

* Reel
* Story
* TikTok Video

## Invoice

Represents creator payment requests.

## Reminder

Represents scheduled notifications.

---

# Current Progress

## Completed

### Project Setup

* Spring Boot project created
* PostgreSQL database designed
* Layered architecture established

### Domain Entities

Implemented:

* Creator
* Brand
* Deal
* DealDeliverable
* Invoice
* Reminder

### Enums

Implemented:

* DealStatus
* DeliverableStatus
* DeliverableType
* InvoiceStatus
* ReminderStatus
* ReminderType

### Repositories

Implemented repositories for:

* Creator
* Brand
* Deal
* Deliverable
* Invoice
* Reminder

### DTO Layer

Implemented DTOs for:

* Creator
* Brand
* Deal

### Mapper Layer

Implemented:

* CreatorMapper
* BrandMapper
* DealMapper

### Services

Implemented:

* CreatorService
* BrandService
* DealService

### Controllers

Implemented:

* CreatorController
* BrandController
* DealController

### Exception Handling

Implemented:

* ResourceNotFoundException
* DuplicateResourceException
* GlobalExceptionHandler

---

# Next Steps

## Phase 1

### Deliverables Feature

Build:

* Deliverable CRUD
* Deliverable status updates
* Deliverable tracking

---

### Invoice Feature

Build:

* Invoice CRUD
* Payment status updates
* Invoice reports

---

### Reminder Feature

Build:

* Scheduled reminders
* Email integration

---

## Phase 2

### Config Server

Implement centralized configuration.

### Eureka Server

Implement service discovery.

### API Gateway

Implement routing and gateway.

---

## Phase 3

### RabbitMQ

Implement asynchronous messaging.

### Kafka

Implement event streaming.

---

## Phase 4

### OAuth2

Implement authentication and authorization.

### Keycloak

Identity management integration.

---

## Phase 5

### Observability

Implement:

* Prometheus
* Grafana
* Loki
* Tempo

---

## Phase 6

### AI Features

Implement:

* Contract Analyzer
* Deal Assistant
* Email Generator

---

# Long-Term Goal

Build a production-ready SaaS platform while learning:

* Spring Boot
* Spring Cloud
* Microservices
* Event-Driven Architecture
* Distributed Systems
* OAuth2
* Observability
* AI Integration
* SaaS Design

The final project should demonstrate enterprise-grade architecture and serve as a complete portfolio project.

One thing I would change in the next iteration: split Creator, Brand, and Deal into separate microservices after you finish the CRUD features. Right now keeping them together is correct because you're still building the business logic, but later it will give you a real reason to practice Eureka, Gateway, Feign Clients, Resilience4J, RabbitMQ, and Kafka.