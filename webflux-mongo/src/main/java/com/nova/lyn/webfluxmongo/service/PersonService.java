package com.nova.lyn.webfluxmongo.service;

import com.mongodb.util.JSON;
import com.nova.lyn.webfluxmongo.dao.PersonRespository;
import com.nova.lyn.webfluxmongo.entity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName PersonService
 * @Description TODO
 * @Author Lyn
 * @Date 2019/4/4 0004 上午 8:50
 * @Version 1.0
 */
@Service
public class PersonService {

    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    private IpService ipService;
    @Autowired
    private PersonRespository personRespository;

    public PersonService(IpService ipService, PersonRespository personRespository) {
        this.ipService = ipService;
        this.personRespository = personRespository;
    }

    public Mono<Person> addPerson(Mono<Person> mono) {
        return mono.flatMap(this::addIpInfo)
                .flatMap(personRespository::save);
    }

    private Mono<Person> addIpInfo(Person person) {
        return ipService.getIpInfo(person.getIp())
                .map(ipInfo -> person.copyWithIpInfo(JSON.parse(ipInfo)));
    }

    public void updateInfo() {
        LocalDateTime dateTime = LocalDateTime.now().minusDays(90);
        personRespository.findPersonByUpdatedAtLessThan(dateTime)
                .buffer(300)
                .onBackpressureBuffer(5000)
                .parallel(2)
                .flatMap(this::updateIpInfo)
                .subscribe(
                        p -> logger.info("Updated IP information for person with id {}", p.getId()),
                        t -> logger.error("Failed IP information update stream", t)
                );
    }

    private Flux<Person> updateIpInfo(List<Person> personList) {
        return personList.stream()
                .map(this::addIpInfo)
                .map(monoP -> monoP.flux())
                .reduce(Flux.empty(), (p1, p2) -> p1.concatWith(p2))
                .flatMap(personRespository::save);
    }

}
