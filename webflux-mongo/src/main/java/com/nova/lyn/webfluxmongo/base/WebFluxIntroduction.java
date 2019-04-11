package com.nova.lyn.webfluxmongo.base;

import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;

/***
 * @ClassName: WebFluxIntroduction
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2019/4/11 下午9:51
 * @version : V1.0
 */
public class WebFluxIntroduction {

    public  void process(String ...arg) {
        Flux.range(0, arg.length).subscribe(System.out::println);
    }

    public void testmethod() {
        Flux.fromStream(Arrays.asList("g","b","b").stream()).subscribe(this::process);
    }

    public static void main(String[] args) {

        Flux.fromArray(new String[]{"a","b","c"}).subscribe(System.out::println);

        Flux.empty().subscribe(System.out::println);

        new WebFluxIntroduction().testmethod();

    }
}
