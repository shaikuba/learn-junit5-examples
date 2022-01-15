package learn.junit5.shopping.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Person {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }


//    public static Person getPerson(String name) {
//        return new Person(name, -1);
//    }
}
