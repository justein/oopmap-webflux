package com.nova.lyn.webfluxmongo.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;

/**
 * @ClassName MongoConfiguration
 * @Description TODO
 * @Author Lyn
 * @Date 2019/4/3 0003 下午 4:03
 * @Version 1.0
 */
@Configuration
public class MongoConfiguration extends AbstractReactiveMongoConfiguration {
    @Override
    public MongoClient reactiveMongoClient() {
        return MongoClients.create();
    }

    @Override
    protected String getDatabaseName() {
        return "reactive";
    }
}
