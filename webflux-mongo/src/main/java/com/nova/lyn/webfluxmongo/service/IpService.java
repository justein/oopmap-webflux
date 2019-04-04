package com.nova.lyn.webfluxmongo.service;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

/**
 * @ClassName IpService
 * @Description TODO
 * @Author Lyn
 * @Date 2019/4/4 0004 下午 2:01
 * @Version 1.0
 */
@Service
public class IpService {

    private static final Logger logger = LoggerFactory.getLogger(IpService.class);

    public static final String EMPTY_IP_INFO = "{}";
    @Autowired
    private CircuitBreaker circuitBreaker;
    @Autowired
    private PrimaryIpService primaryIpService;
    @Autowired
    private BackupIpService backupIpService;

    public Mono<String> getIpInfo(String ip) {
        return Try.of(CircuitBreaker.decorateCheckedSupplier(circuitBreaker, () ->
                primaryIpService.requestIpInfo(ip).onErrorResume(t -> {
                    circuitBreaker.onError(01, t);
                    return backupIpService.requestIpInfo(ip);
                })
        )).getOrElse(() -> backupIpService.requestIpInfo(ip))
                .flatMap(this::parse)
                .doOnError(t -> logger.error("Failed to obtain or parse ip info", t))
                .onErrorReturn(EMPTY_IP_INFO);
    }

    private Mono<String> parse(ClientResponse response) {
        if (response.statusCode().is2xxSuccessful()) {
            return response.bodyToMono(String.class);
        }else {
            throw new IllegalArgumentException("Request for ip info failed: " + response);
        }
    }
}
