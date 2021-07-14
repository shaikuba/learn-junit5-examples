package junit.learning.parameterized;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.junit5.learning.example.shopping.enums.Mobile;
import org.junit5.learning.example.shopping.model.Person;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

@Slf4j
public class ParameterizedExamples {


    /**
     * ValueSource
     * - 可以为测试提供一组基础类型的参数数据，包括short, byte, int, long, float, double, char, boolean, java.lang.String, java.lang.Class
     * - 每次测试方法迭代仅提供一个测试参数
     * - @ValueSource 注解中仅允许提供一种参数类型
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void valueSourceExample(int value) {
        log.info("value={}", value);
    }


    /**
     * NullSource
     * - 通过NullSource注解为测试方法提供null参数值
     * - 测试参数类型不能为基本数据类型
     */
    @ParameterizedTest
    @NullSource
    void nullSourceExample(String value) {
        log.info("value={}", value);
    }

    /**
     * EmptySource
     * - EmptySource为测试方法提供空值
     * - 空值参数数据类型包括java.lang.String, java.util.List, java.util.Set, java.util.Map 以及基本数据类型或对象类型数组，例如int[], char[][], String[]等
     * - 不支持所支持的数据类型的子类型
     */
    @ParameterizedTest
    @EmptySource
    void emptySourceExample(Map array) {
        log.info("value={}", array.size());
    }

    /**
     * EnumSource
     * - 通过EnumSource为测试方法提供枚举常量参数
     * - 该注解支持四种枚举常量过滤模式，包括INCLUDE(default), EXCLUDE, MATCH_ALL, MATCH_ANY
     */
    @ParameterizedTest
    @EnumSource(value = Mobile.class, names = {"HUA.*", "APP.*"}, mode = EnumSource.Mode.MATCH_ANY)
    void enumSourceExample(Mobile mobile) {
        log.info("value={}", mobile.name());
    }


    /**
     * CsvSource
     * - 为测试方法指定一组测试参数
     * - 默认使用“,”分割一组参数
     * - 参数值包含空格分隔符时，使用单引号进行字符串转义
     * - 对于null值的使用，保留参数为空
     * - 对于空字符串的使用，保留参数为’’
     */
    @ParameterizedTest
    @CsvSource(value = {"venus, 22"
            , "jupiter, 23"
            , "mercury,NU"
            , "mars, 25"}
            , nullValues = {"NU"})
    void csvSourceExample(String name, Integer age) {
        log.info("name: {}, age: {}", name, age);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/datas.csv", numLinesToSkip = 1)
    void csvFileSourceExample(String name, int age) {
        log.info("name: {}, age: {}", name, age);
    }


    //-------------------------------
    // Method Source               //
    //-------------------------------
    @ParameterizedTest
    @MethodSource(value = "persons_array")
    void methodSourceExample_1(Person person) {
        log.info("Person name: {}, age: {}", person.getUsername(), person.getAge());
    }
    static Person[] persons_array() {
        return new Person[]{new Person("jupiter", 21), new Person("venus", 22)};
    }

    @ParameterizedTest
    @MethodSource(value = "person_stream")
    void methodSourceExample_2(Person person) {
        log.info("Person name: {}, age: {}", person.getUsername(), person.getAge());
    }
    static Stream<Arguments> person_stream() {
        return Stream
                .of(new Person("jupiter", 21), new Person("venus", 22))
                .map(Arguments::of);
    }

    @ParameterizedTest
    @MethodSource(value = "multitype_stream")
    void methodSourceExample_3(Person person, Mobile mobile) {
        log.info("Person name: {}, age: {}", person.getUsername(), person.getAge());
        log.info("Mobile: {}", mobile.name());
    }
    static Stream<Arguments> multitype_stream() {
        return Stream.of(arguments(new Person("jupiter", 21), Mobile.APPLE), arguments(new Person("venus", 22), Mobile.HUAWEI));
    }

    /**
     * ArgumentsSource
     */
    @ParameterizedTest
    @ArgumentsSource(value = PersonArgumentsProvider.class)
    void argumentsSourceExample(Person person) {
        log.info("Person name: {}, age: {}", person.getUsername(), person.getAge());
    }

}

class PersonArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(new Person("venus", 22), new Person("ray", 23), new Person("jupiter", 24)).map(Arguments::of);
    }
}
