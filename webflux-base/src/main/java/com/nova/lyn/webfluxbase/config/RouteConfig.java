package com.nova.lyn.webfluxbase.config;

import com.nova.lyn.webfluxbase.handler.TimeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/***
 * @ClassName: RouteConfig
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2019/4/2 下午10:12
 * @version : V1.0
 */
@Component
public class RouteConfig {

    @Autowired
    private TimeHandler timeHandler;

    @Bean
    public RouterFunction<ServerResponse> router() {
        return route(GET("/time"), req -> timeHandler.getTime(req))
                .andRoute(GET("/date"), timeHandler::getDate);
    }
}
