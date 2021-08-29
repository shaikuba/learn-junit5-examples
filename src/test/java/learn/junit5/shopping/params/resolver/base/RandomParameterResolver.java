package learn.junit5.shopping.params.resolver.base;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.lang.reflect.Parameter;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * RandomParametersExtension
 *
 * @author Ray.Xu
 */
public class RandomParameterResolver implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return parameterContext.isAnnotated(Random.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return getRandomValue(parameterContext.getParameter(), extensionContext);
    }

    private Object getRandomValue(Parameter parameter, ExtensionContext extensionContext) {
        Class<?> type = parameter.getType();
//		java.util.Random randomUtil = extensionContext.getRoot().getStore(ExtensionContext.Namespace.GLOBAL)
//				.getOrComputeIfAbsent(java.util.Random.class);

        Random random = parameter.getAnnotationsByType(Random.class)[0];

        if (random.type().equals(Random.RandomType.ALPHANUMERIC)) {
            checkArgument(random.length() > 0, "length must be positive value.");
            return RandomUtils.alphanumeric(random.length());
        }

        if (random.type().equals(Random.RandomType.CHARACTERS)) {
            checkArgument(random.length() > 0, "length must be positive value.");
            return RandomUtils.letters(random.length());
        }

        if (random.type().equals(Random.RandomType.INTEGRAL)) {
            if (random.min() == -1 && random.max() == -1) {
                return new java.util.Random().nextInt();
            } else if (random.min() != -1 && random.max() != -1) {
                return RandomUtils.nextInt(random.min(), random.max());
            } else if (random.min() != -1) {
                return RandomUtils.nextInt(random.min(), Integer.MAX_VALUE);
            } else {//random.max() != -1
                return RandomUtils.nextInt(Integer.MIN_VALUE, random.max());
            }

        }
        if (random.type().equals(Random.RandomType.DECIMAL)) {
            if (random.min() == -1 && random.max() == -1) {
                return new java.util.Random().nextDouble();
            } else if (random.min() != -1 && random.max() != -1) {
                return RandomUtils.nextDouble(random.min(), random.max());
            } else if (random.min() != -1) {
                return RandomUtils.nextDouble(random.min(), Double.MAX_VALUE);
            } else {//random.max() != -1
                return RandomUtils.nextDouble(Double.MIN_VALUE, random.max());
            }
        }
        throw new ParameterResolutionException("No random generator implemented for " + type);
    }

}