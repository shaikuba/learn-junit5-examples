package learn.junit5.shopping.injection.resolver.base;

import org.junit.jupiter.api.extension.ParameterResolutionException;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.function.Function;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * RandomTypeHandler
 *
 * Generate random type according to the {@link learn.junit5.shopping.injection.resolver.base.Random.RandomType}
 */
public class RandomTypeHandler {
        
        private static final EnumMap<Random.RandomType, Function<Random, Object>> RANDOM_TYPE_HANDLER_MAP = new EnumMap(Random.RandomType.class);

        static {
            Arrays.asList(Random.RandomType.values()).forEach(randomType -> {
                switch (randomType) {
                    case ALPHANUMERIC:
                        RANDOM_TYPE_HANDLER_MAP.put(randomType, random -> handleAlphanumeric(random));
                        break;
                    case DECIMAL:
                        RANDOM_TYPE_HANDLER_MAP.put(randomType, random -> handleDecimal(random));
                        break;
                    case INTEGRAL:
                        RANDOM_TYPE_HANDLER_MAP.put(randomType, random -> handleIntegral(random));
                        break;
                    case CHARACTERS:
                        RANDOM_TYPE_HANDLER_MAP.put(randomType, random -> handleCharacters(random));
                        break;
                }
            });
        }

        public Object handleRandomParameter(Random random) {
            return RANDOM_TYPE_HANDLER_MAP.computeIfAbsent(random.type(), (_random) -> {
                throw new ParameterResolutionException("No random generator implemented for " + _random);
            }).apply(random);
        }

        private static Object handleAlphanumeric(Random random) {
            checkArgument(random.length() > 0, "length must be positive value.");
            return RandomUtils.alphanumeric(random.length());
        }

        private static Object handleCharacters(Random random) {
            checkArgument(random.length() > 0, "length must be positive value.");
            return RandomUtils.letters(random.length());
        }

        private static Object handleIntegral(Random random) {
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

        private static Object handleDecimal(Random random) {
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
    }