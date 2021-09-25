package learn.junit5.shopping.injection;

import learn.junit5.shopping.injection.resolver.RandomParameterResolver;
import learn.junit5.shopping.injection.resolver.base.Random;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@ExtendWith(RandomParameterResolver.class)
class ParametersResolverExamples {

    @Test
    void random_alphanumeric_example(@Random(type = Random.RandomType.ALPHANUMERIC, length = 5) String randomStr
    , @Random(type = Random.RandomType.ALPHANUMERIC) String another) {
        log.info("random value: {}", randomStr);
        log.info("random2 value: {}", another);
        assertEquals(5, randomStr.length());
    }

    @Test
    void random_alphanumeric_example_chars(@Random(type = Random.RandomType.CHARACTERS, length = 5) String randomStr) {
        log.info("random value: {}", randomStr);
        assertEquals(5, randomStr.length());
    }

}
