package com.nova.lyn.webfluxbase.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/***
 * @ClassName: WebFluxController
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2019/4/2 下午9:51
 * @version : V1.0
 */

@RestController
public class WebFluxController {

    @GetMapping("/hello")
    public Mono<String> hello() {
        return Mono.just("hello webflux!");
    }
}
