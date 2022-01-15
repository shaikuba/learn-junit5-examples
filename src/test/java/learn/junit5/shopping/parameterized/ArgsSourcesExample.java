package learn.junit5.shopping.parameterized;

import learn.junit5.shopping.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.provider.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.text.MessageFormat.format;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@Slf4j
public class ArgsSourcesExample {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    void valueSourceTest(int i) {
        log.info("{}", i);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void nullSource(Object[] param) {
        log.info("param: {}", param == null ? null : param.length);
    }

    @ParameterizedTest
    @EnumSource(value = ChronoUnit.class, names = {".*DAY.*", ".*HOUR.*"}, mode = EnumSource.Mode.MATCH_ANY)
    void enumSourceExample(ChronoUnit unit) {
        log.info(unit.name());
    }


    @ParameterizedTest
    @CsvSource(value = {"1,2,NA", "4,NL,6"}, nullValues = {"NA", "NL"})
    void csvSourceTest(String i1, String i2, String i3) {
        log.info("{}, {}, {}\n", i1, i2, i3);
    }

    @ParameterizedTest
    @CsvSource(value = {"'hello, word',2,3", "hello,\"\",3", "hello,'',3", "hello,,3", "hello,NA,3"}, nullValues = {"NA"})
    void csvSourceStringTest(String i1, String i2, Integer i3) {
        log.info("{}, {}, {}", i1, i2, i3);
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/datas.csv"}, numLinesToSkip = 2)
    void csvFileSourceStringTest(String i1, int i2) {
        log.info("{}, {}", i1, i2);
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/datas.csv"})
    void csvFileSourceToArgumentsAccessor(ArgumentsAccessor argumentsAccessor) {
        log.info("{}, {}", argumentsAccessor.getString(0), argumentsAccessor.get(1));
    }


    @ParameterizedTest
    @CsvFileSource(resources = {"/datas.csv"}, numLinesToSkip = 1)
    void csvFileSourceToPerson(@AggregateWith(PersonAggregator.class) Person person) {
        log.info("{}, {}", person.getName(), person.getAge());
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/datas.csv"}, numLinesToSkip = 1)
    void csvFileSourceToPersonAnnotation(@CsvToPerson Person person) {
        log.info("{}, {}", person.getName(), person.getAge());
    }


    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.PARAMETER)
    @AggregateWith(PersonAggregator.class)
    public @interface CsvToPerson {
    }

    public static class PersonAggregator implements ArgumentsAggregator {

        @Override
        public Person aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) throws ArgumentsAggregationException {
            Person person = new Person();
            person.setName(accessor.getString(0));
            person.setAge(accessor.getInteger(1));

            return person;
        }
    }


    //******************
    // Parameter method with one single parameter
    // T[], Stream<T>, Iterable<T>, Iterator<T>
    //*******************//
    @ParameterizedTest
    @MethodSource(value = "animals")
    void methodSourceTest1_1(String name) {
        log.info(name);
    }

    static String[] animals() {
        return new String[]{"dog", "cat", "elephant"};
    }

    @ParameterizedTest
    @MethodSource(value = "fruits")
    void methodSourceTest1_2(String fruit) {
        log.info(fruit);
    }

    static Stream<String> fruits() {
        return Stream.of("apple", "pear", "banana");
    }

    @ParameterizedTest
    @MethodSource(value = "fruits1")
    void methodSourceTest1_3(String fruit) {
        log.info(fruit);
    }

    static Iterable<String> fruits1() {
        return Stream.of("apple", "pear", "banana").collect(Collectors.toList());
    }

    @ParameterizedTest
    @MethodSource(value = "fruits2")
    void methodSourceTest1_4(String fruit) {
        log.info(fruit);
    }

    static Iterator<String> fruits2() {
        return Stream.of("apple", "pear", "banana").iterator();
    }

    //******************
    // Parameter method with multi type parameter
    //*******************//
    @ParameterizedTest
    @MethodSource(value = "multiTypes3")
    void methodSourceTest2_4(String name, Integer price, List<String> colors) {
        log.info("name: {}, price: {}, colors: {}", name, price, colors.toString());
    }

    static Object[][] multiTypes3() {
        return new Object[][]{new Object[]{"apple", 12, Arrays.asList("red", "green", "yellow")}, new Object[]{"pear", 15, Arrays.asList("red", "green", "yellow")}};
    }

    @ParameterizedTest
    @MethodSource(value = "multiTypes4")
    void methodSourceTest2_5(String name, Integer price, List<String> colors) {
        log.info("name: {}, price: {}, colors: {}", name, price, colors.toString());
    }

    static Stream<Object[]> multiTypes4() {
        return Stream.of(new Object[]{"apple", 12, Arrays.asList("red", "green", "yellow")}, new Object[]{"pear", 15, Arrays.asList("red", "green", "yellow")});
    }

    @ParameterizedTest
    @MethodSource(value = "multiTypes2")
    void methodSourceTest2_3(String name, Integer price, List<String> colors) {
        log.info("name: {}, price: {}, colors: {}", name, price, colors.toString());
    }

    static Stream<Arguments> multiTypes2() {
        return Stream.of(arguments("apple", 12, Arrays.asList("red", "green", "yellow")), arguments("pear", 15, Arrays.asList("red", "green", "yellow")));
    }

    @ParameterizedTest
    @MethodSource(value = "person2")
    void methodSourceTest2_4(Person person) {
        log.info(person.getName() + ", " + person.getAge());
    }

    static Stream<Person> person2() {
        return Stream.of(new Person("ray", 22), new Person("venus", 22));
    }


    @ParameterizedTest
    @ArgumentsSource(value = MyArgumentsSourceProvider.class)
    void argumentsProvider(Person person1, Person person2, Person person3) {
        log.info("name: {}, age: {}", person1.getName(), person1.getAge());
        log.info("person2 -> name: {}, age: {}", person2.getName(), person2.getAge());
        log.info("person3 -> name: {}, age: {}", person3.getName(), person3.getAge());
    }


}

class MyArgumentsSourceProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {

        return Arrays.asList(
                arguments(new Person("venus1", 22), new Person("ray1", 23), new Person("jupiter1", 24))
                , arguments(new Person("venus2", 22), new Person("ray2", 23), new Person("jupiter2", 24))
                , arguments(new Person("venus3", 22), new Person("ray3", 23), new Person("jupiter3", 24))
        ).stream();


//        return Stream.of(
//                new Person("venus", 22)
//                , new Person("ray", 23)
//                , new Person("jupiter", 24)
//        )
//                .map(Arguments::of);
    }
}