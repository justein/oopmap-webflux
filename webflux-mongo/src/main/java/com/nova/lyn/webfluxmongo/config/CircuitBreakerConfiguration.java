package com.nova.lyn.webfluxmongo.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * @ClassName CircuitBreakerConfiguration
 * @Description TODO
 * @Author Lyn
 * @Date 2019/4/3 0003 下午 4:08
 * @Version 1.0
 */

@Configuration
public class CircuitBreakerConfiguration {

    @Bean
    public CircuitBreaker ipServiceCircuitBreaker() {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .waitDurationInOpenState(Duration.ofMinutes(5))
                .ringBufferSizeInClosedState(100)
                .ringBufferSizeInHalfOpenState(30)
                .failureRateThreshold(10)
                .build();
        return CircuitBreaker.of("ipService", circuitBreakerConfig);
    }
}
