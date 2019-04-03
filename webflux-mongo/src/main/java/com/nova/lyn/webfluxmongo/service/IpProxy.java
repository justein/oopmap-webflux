package com.nova.lyn.webfluxmongo.service;

import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

/**
 * @ClassName IpProxy
 * @Description TODO
 * @Author Lyn
 * @Date 2019/4/3 0003 下午 5:49
 * @Version 1.0
 */
public interface IpProxy {

    Mono<ClientResponse> requestIpInfo(String ip);
}
