package com.nova.lyn.webfluxmongo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @ClassName PrimaryIpService
 * @Description TODO
 * @Author Lyn
 * @Date 2019/4/3 0003 下午 5:51
 * @Version 1.0
 */
@Component
public class BackupIpService implements IpProxy {

    private static final Logger logger = LoggerFactory.getLogger(BackupIpService.class);
    @Value("${ip.fallback.service.url}")
    private String ipServiceUrl;

    @Override
    public Mono<ClientResponse> requestIpInfo(String ip) {
        return WebClient.create(ipServiceUrl).get().uri("/json/{ip}", ip).exchange()
                .doOnError(t -> logger.error("Failed to obtain IP information from backup provider", t));
    }
}
