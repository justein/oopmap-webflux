package com.nova.lyn.webfluxmongo.route;

import com.nova.lyn.webfluxmongo.dao.PersonRespository;
import com.nova.lyn.webfluxmongo.entity.Person;
import com.nova.lyn.webfluxmongo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

/**
 * @ClassName PersonRouter
 * @Description TODO
 * @Author Lyn
 * @Date 2019/4/4 0004 上午 10:50
 * @Version 1.0
 */
@Configuration
public class PersonRouter {

    private final PersonRespository personRespository;
    private final PersonService personService;

    PersonRouter(PersonRespository personRespository, PersonService personService) {
        this.personRespository = personRespository;
        this.personService = personService;
    }

    @Bean
    public RouterFunction personRoutes() {

        return RouterFunctions.route(GET("/person/{id}").and(accept(APPLICATION_JSON)),
                request -> {
                    String personId = request.pathVariable("id");
                    Mono<ServerResponse> notFound = ServerResponse.notFound().build();
                    return personRespository.findById(personId)
                            .flatMap(person -> ServerResponse.ok().body(Mono.just(person), Person.class))
                            .switchIfEmpty(notFound);
                })
                .andRoute(GET("/person").and(accept(APPLICATION_JSON)),
                        request ->
                    ServerResponse.ok().body(personRespository.findAll(), Person.class)
                )
                .andRoute(POST("/person").and(contentType(APPLICATION_JSON)),
                        request ->
                    ServerResponse.ok().body(personService.addPerson(request.bodyToMono(Person.class)),Person.class)
                )
                .andRoute(POST("/person/ip_info/update"),
                        request -> {
                            personService.updateInfo();
                            return ServerResponse.ok().build();
                        }
                );
    }
}
