package learn.junit5.shopping.parameterized.converter;

import learn.junit5.shopping.model.Person;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.TypedArgumentConverter;

public class ToPersonArgumentConverter extends TypedArgumentConverter<String, Person> {


    protected ToPersonArgumentConverter() {
        super(String.class, Person.class);
    }

    @Override
    protected Person convert(String name) throws ArgumentConversionException {
        return new Person(name, -1);
    }

}
