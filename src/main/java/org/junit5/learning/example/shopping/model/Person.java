package org.junit5.learning.example.shopping.model;

import lombok.Data;

@Data
public class Person {

    private Long id;

    private String username;

    private String nickname;

    private int age;

    public Person() {}

    public Person(String username, int age) {
        this.username = username;
        this.age = age;
    }


}
