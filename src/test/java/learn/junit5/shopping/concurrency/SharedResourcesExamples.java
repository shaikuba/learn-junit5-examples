package learn.junit5.shopping.concurrency;

import learn.junit5.shopping.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.api.parallel.ResourceAccessMode;
import org.junit.jupiter.api.parallel.ResourceLock;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Execution(ExecutionMode.CONCURRENT)
public class SharedResourcesExamples {

    final static String GLOBAL_USERS = "learn.junit5.shopping.concurrency.persons";

    @BeforeEach
    void initiate() {
        GlobalPersons.clear();
    }

    @Test
    @ResourceLock(value = GLOBAL_USERS, mode = ResourceAccessMode.READ)
    void is_empty_test() {
        log.info("is_empty_test() : {}" + GlobalPersons.getPersons());
        assertTrue(GlobalPersons.getPersons().isEmpty());
    }

    @Test
    @ResourceLock(value = GLOBAL_USERS, mode = ResourceAccessMode.READ_WRITE)
    void add_test() {
        GlobalPersons.add(1, new Person("peter", 22));
        log.info("add_test() : {}" + GlobalPersons.getPersons());
        assertEquals("peter", GlobalPersons.get(1).getName());
    }

    @Test
    @ResourceLock(value = GLOBAL_USERS, mode = ResourceAccessMode.READ_WRITE)
    void update_test() {
        GlobalPersons.update(1, new Person("john", 23));
        log.info("update_test() : {}" + GlobalPersons.getPersons());
        assertEquals("john", GlobalPersons.get(1).getName());
    }

    @Test
    @ResourceLock(value = GLOBAL_USERS, mode = ResourceAccessMode.READ_WRITE)
    void remove_Test() {
        GlobalPersons.add(2, new Person("Anand", 21));
        GlobalPersons.remove(2);
        log.info("remove_test() {}: " + GlobalPersons.getPersons());
        assertNull(GlobalPersons.get(2));
    }
}