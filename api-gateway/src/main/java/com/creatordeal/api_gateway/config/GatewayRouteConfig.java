package com.creatordeal.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class GatewayRouteConfig {

    public RouteLocator createDealRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("creator-service", route ->
                        route
                                .path("/api/creators/**")
                                .filters(filter -> filter.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                                .uri("lb://creator-service")
                )
                .route("brand-service", route -> route
                        .path("/api/brands/**")
                        .filters(filter -> filter
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://brand-service"))
                .route("deal-service-deals", route -> route
                        .path("/api/deals/**")
                        .filters(filter -> filter
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://deal-service"))

                .route("deal-service-deliverables", route -> route
                        .path("/api/deliverables/**")
                        .filters(filter -> filter
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://deal-service"))
                .route("invoice-service", route -> route
                        .path("/api/invoices/**")
                        .filters(filter -> filter
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://invoice-service"))
                .route("notification-service", route -> route
                        .path("/api/reminders/**")
                        .filters(filter -> filter
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://notification-service"))
                .build();


    }


}
