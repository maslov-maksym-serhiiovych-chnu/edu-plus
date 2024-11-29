package ua.edu.chnu.api_gateway.configs;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration(proxyBeanMethods = false)
public class ApiGatewayConfig {
    @Bean
    public RouterFunction<ServerResponse> routeCoursesApi() {
        return GatewayRouterFunctions.route("courses-api")
                .route(RequestPredicates.path("api/courses/**"), HandlerFunctions.http("http://courses-api:8080"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> routeCommentsApi() {
        return GatewayRouterFunctions.route("comments-api")
                .route(RequestPredicates.path("api/comments/**"), HandlerFunctions.http("http://comments-api:8081"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> routeTasksApi() {
        return GatewayRouterFunctions.route("tasks-api")
                .route(RequestPredicates.path("api/tasks/**"), HandlerFunctions.http("http://tasks-api:8082"))
                .build();
    }
}
