package learn.junit5.shopping.basic.interfase;

public interface ServiceTest<S> extends RandomInjector, TestLifecycleLogger {

    S createService();

}
