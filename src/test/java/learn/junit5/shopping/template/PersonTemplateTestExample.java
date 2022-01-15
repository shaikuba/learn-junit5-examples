package learn.junit5.shopping.template;

import learn.junit5.shopping.model.Person;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonTemplateTestExample {


    @TestTemplate
    @ExtendWith(PersonTestTemplateInvocationContextProvider.class)
    void person_template_test(Person person) {

        assertTrue(person.getName().length() != 0);
    }

}
