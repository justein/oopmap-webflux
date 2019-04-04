package com.nova.lyn.webfluxmongo.dao;

import com.nova.lyn.webfluxmongo.entity.Person;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @ClassName PersonRespository
 * @Description TODO
 * @Author Lyn
 * @Date 2019/4/4 0004 上午 8:45
 * @Version 1.0
 */
public interface PersonRespository extends ReactiveCrudRepository<Person, String> {

    @Query
    Flux<Person> findPersonByUpdatedAtLessThan(LocalDateTime updatedTime);
}
