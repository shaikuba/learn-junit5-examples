package learn.junit5.shopping.parameterized;

import learn.junit5.shopping.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

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
    void nullSource(String param) {
        log.info("param: {}", param);
    }

    @ParameterizedTest
    @EnumSource(value = ChronoUnit.class, names = {".*DAY.*"}, mode = EnumSource.Mode.MATCH_ALL)
    void enumSourceExample(ChronoUnit unit) {
        log.info(unit.name());
    }


    @ParameterizedTest
    @CsvSource(value = {"1,2,3", "4,5,6"})
    void csvSourceTest(int i1, int i2, int i3) {
        log.info("{}, {}, {}\n", i1, i2, i3);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "'hello, word',2,3"
            , "hello,\"\",3"
            , "hello,'',3"
            , "hello,,3"
            , "hello,NA,3"
    }, nullValues = {"NA"})
    void csvSourceStringTest(String i1, String i2, Integer i3) {
        log.info("{}, {}, {}", i1, i2, i3);
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/datas.csv"})
    void csvFileSourceStringTest(String i1, int i2, Integer nullValue, String emptyString) {
        System.out.printf("%s,%d,%d, %s\n", i1, i2, nullValue, emptyString);
    }

    //******************
    // Parameter method with one single parameter
    // T[], Stream<T>, Iterable<T>, Iterator<T>
    //*******************//
    @ParameterizedTest
    @MethodSource(value = "animals")
    void methodSourceTest1_1(String name) {
        System.out.println(name);
    }
    static String[] animals() {
        return new String[]{"dog", "cat", "elephant"};
    }

    @ParameterizedTest
    @MethodSource(value = "fruits")
    void methodSourceTest1_2(String fruit) {
        System.out.println(fruit);
    }
    static Stream<String> fruits() {
        return Stream.of("apple", "pear", "banana");
    }

    @ParameterizedTest
    @MethodSource(value = "fruits1")
    void methodSourceTest1_3(String fruit) {
        System.out.println(fruit);
    }
    static Iterable<String> fruits1() {
        return Stream.of("apple", "pear", "banana").collect(Collectors.toList());
    }

    @ParameterizedTest
    @MethodSource(value = "fruits2")
    void methodSourceTest1_4(String fruit) {
        System.out.println(fruit);
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
        System.out.println(format("name: {0}, price: {1}, colors: {2}", name, price, colors.toString()));
    }
    static Object[][] multiTypes3() {
        return new Object[][]{new Object[]{"apple", 12, Arrays.asList("red", "green", "yellow")}, new Object[]{"pear", 15, Arrays.asList("red", "green", "yellow")}};
    }

    @ParameterizedTest
    @MethodSource(value = "multiTypes4")
    void methodSourceTest2_5(String name, Integer price, List<String> colors) {
        System.out.println(format("name: {0}, price: {1}, colors: {2}", name, price, colors.toString()));
    }
    static Stream<Object[]> multiTypes4() {
        return Stream.of(new Object[]{"apple", 12, Arrays.asList("red", "green", "yellow")}, new Object[]{"pear", 15, Arrays.asList("red", "green", "yellow")});
    }

    @ParameterizedTest
    @MethodSource(value = "person")
    void methodSourceTest2_1(String name, Integer age) {
        System.out.println(name + ", " + age);
    }
    static Stream<Object[]> person() {
        return Stream.of(new Object[]{"ray", 22}, new Object[]{"venus", 22});
    }

    @ParameterizedTest
    @MethodSource(value = "multiTypes1")
    void methodSourceTest2_2(String name, Integer price, List<String> colors) {
        System.out.println(format("name: {0}, price: {1}, colors: {2}", name, price, colors.toString()));
    }
    static Stream<Object[]> multiTypes1() {
        return Stream.of(new Object[]{"apple", 12, Arrays.asList("red", "green", "yellow")}, new Object[]{"pear", 15, Arrays.asList("red", "green", "yellow")});
    }

    @ParameterizedTest
    @MethodSource(value = "multiTypes2")
    void methodSourceTest2_3(String name, Integer price, List<String> colors) {
        System.out.println(format("name: {0}, price: {1}, colors: {2}", name, price, colors.toString()));
    }
    static Stream<Arguments> multiTypes2() {
        return Stream.of(arguments("apple", 12, Arrays.asList("red", "green", "yellow")), arguments("pear", 15, Arrays.asList("red", "green", "yellow")));
    }

    @ParameterizedTest
    @MethodSource(value = "person2")
    void methodSourceTest2_4(Person person) {
        System.out.println(person.getName() + ", " + person.getAge());
    }
    static Stream<Person> person2() {
        return Stream.of(new Person("ray", 22), new Person("venus", 22));
    }


    @ParameterizedTest
    @ArgumentsSource(value = MyArgumentsSourceProvider.class)
    void argumentsProvider(Person person) {
        System.out.println(person.getName() + ": " + person.getAge());
    }


}

class MyArgumentsSourceProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(new Person("venus", 22), new Person("ray", 23), new Person("jupiter", 24)).map(Arguments::of);
    }
}