package learn.junit5.shopping.basic.lifecycle;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaObjectLifeCycle {

    static String greatMsg = "Hello";

    private String name = "JavaObjectLifeCycle";

    static {
        log.info("static field: {}", greatMsg);
    }

    {
        log.info("name = {}", name);
    }

    public JavaObjectLifeCycle() {
        log.info("constructor init");
    }


    public static void main(String[] args) {
        new JavaObjectLifeCycle();
        new JavaObjectLifeCycle();
    }
}
