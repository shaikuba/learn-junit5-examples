package learn.junit5.shopping.basic.interfase;

import learn.junit5.shopping.injection.resolver.RandomParameterResolver;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(value = RandomParameterResolver.class)
public interface RandomInjector {
}
