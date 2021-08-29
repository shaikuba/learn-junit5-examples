package learn.junit5.shopping.params.resolver.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Random {

    RandomType type();

    /**
     * It takes effective only when {@link Random#type()} is not numerical.
     */
    int length() default 10;

    int min() default -1;

    int max() default -1;

    enum RandomType {
        ALPHANUMERIC,
        CHARACTERS,
        INTEGRAL,
        DECIMAL
    }
}
