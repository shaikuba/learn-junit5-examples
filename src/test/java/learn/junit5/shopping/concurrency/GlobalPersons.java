package learn.junit5.shopping.concurrency;

import learn.junit5.shopping.model.Person;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GlobalPersons {

    private static Map<Integer, Person> GLOBAL_PERSONS = new HashMap<>();

    public static Person get(int id) {
        return GLOBAL_PERSONS.get(id);
    }

    public static void add(int id, Person person) {
        GLOBAL_PERSONS.put(id, person);
    }

    public static void update(int id, Person person) {
        GLOBAL_PERSONS.put(id, person);
    }

    public static void remove(int id) {
        GLOBAL_PERSONS.remove(id);
    }

    public static void clear() {
        GLOBAL_PERSONS.clear();
    }

    public static Collection<Person> getPersons() {
        return GLOBAL_PERSONS.values();
    }

    public static void setPersons(Map<Integer, Person> persons) {
        GLOBAL_PERSONS = persons;
    }

}