package learn.junit5.shopping.template;

import learn.junit5.shopping.model.Person;
import org.junit.jupiter.api.extension.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class PersonTestTemplateInvocationContextProvider implements TestTemplateInvocationContextProvider {

    @Override
    public boolean supportsTestTemplate(ExtensionContext context) {
        return true;
    }

    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
        return Stream.of(
                newInvocationContext(new Person("venus", 1))
                , newInvocationContext(new Person("jupiter", 2))
                , newInvocationContext(new Person("mars", 2))
        );
    }

    private TestTemplateInvocationContext newInvocationContext(Person person) {

        return new TestTemplateInvocationContext() {
            @Override
            public String getDisplayName(int invocationIndex) {
                return String.format("%d_%s_%d", invocationIndex, person.getName(), person.getAge());
            }

            @Override
            public List<Extension> getAdditionalExtensions() {
                return Collections.singletonList(new ParameterResolver() {

                    @Override
                    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
                        return person.getClass().isAssignableFrom(parameterContext.getParameter().getType());
                    }

                    @Override
                    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
                        return person;
                    }
                });
            }
        };
    }

}
