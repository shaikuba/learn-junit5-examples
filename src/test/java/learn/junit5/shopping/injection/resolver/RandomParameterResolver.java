package learn.junit5.shopping.injection.resolver;

import learn.junit5.shopping.injection.resolver.base.Random;
import learn.junit5.shopping.injection.resolver.base.RandomTypeHandler;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;

/**
 * RandomParametersExtension
 *
 * @author Ray.Xu
 */
public class RandomParameterResolver implements ParameterResolver {

    private RandomTypeHandler randomTypeHandler = new RandomTypeHandler();

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return parameterContext.isAnnotated(Random.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {

        Random random = parameterContext.getParameter().getAnnotationsByType(Random.class)[0];

        return randomTypeHandler.handleRandomParameter(random);
    }


}