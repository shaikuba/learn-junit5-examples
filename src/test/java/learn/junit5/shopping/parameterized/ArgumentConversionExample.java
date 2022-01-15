package learn.junit5.shopping.parameterized;

import learn.junit5.shopping.model.Person;
import learn.junit5.shopping.parameterized.converter.ToPersonArgumentConverter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.ValueSource;

@Slf4j
public class ArgumentConversionExample {


    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void primitive_conversion(double param) {
        log.info("primitive parameter conversion: {}", param);
    }


    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "3"})
    void implicit_conversion(double param) {
        log.info("primitive parameter conversion: {}", param);
    }

    @ParameterizedTest
    @ValueSource(strings = {"true", "false", "3"})
    void implicit_bool_conversion(boolean param) {
        log.info("primitive parameter conversion: {}", param);
    }

    @ParameterizedTest
    @ValueSource(strings = {"venus", "jupiter", "mars"})
    void implicit_factory_conversion(Person name) {
        log.info("String type to person object: {}", name);
    }

    @ParameterizedTest
    @ValueSource(strings = {"venus", "jupiter", "mars"})
    void explicit_conversion(@ConvertWith(ToPersonArgumentConverter.class) Person name) {
        log.info("String type to person object: {}", name);
    }

}
