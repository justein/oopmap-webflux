package com.nova.lyn.webfluxmongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * @ClassName Person
 * @Description TODO
 * @Author Lyn
 * @Date 2019/4/3 0003 下午 6:01
 * @Version 1.0
 */
@Document
public class Person {

    @Id
    private String id;

    private String firstname;

    private String lastname;

    private String ip;

    private Object ipInfo;

    private LocalDateTime updatedAt;

    private Person() {
    }

    public Person(String id, String firstname, String lastname, String ip, Object ipInfo) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.ip = ip;
        this.ipInfo = ipInfo;
        this.updatedAt = LocalDateTime.now();
    }

    public Person copyWithIpInfo(Object ipInfo) {
        return new Person(id, firstname, lastname, ip, ipInfo);
    }

    public String getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getIp() {
        return ip;
    }

    public Object getIpInfo() {
        return ipInfo;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
