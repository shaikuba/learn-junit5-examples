package learn.junit5.shopping.params.resolver;

import learn.junit5.shopping.params.resolver.base.Random;
import learn.junit5.shopping.params.resolver.base.RandomParameterResolver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(RandomParameterResolver.class)
class ParametersResolverExamples {

    @Test
    void randomAlphanumericExample(@Random(type = Random.RandomType.ALPHANUMERIC, length = 5) String randomStr) {
        System.out.println(randomStr);
        assertEquals(5, randomStr.length());
    }
}
