package learn.junit5.shopping.basic.lifecycle;

public class JavaObjectLifeCycle {

    static String greatMsg = "Hello";

    static {
        System.out.printf("static field: %s\n", greatMsg);
        System.out.println("static");
    }

    private String name = "JavaObjectLifeCycle";

    public JavaObjectLifeCycle() {
        System.out.printf("private field: %s\n", name);
        System.out.println("constructor init");
    }


    public static void main(String[] args) {
        JavaObjectLifeCycle objectLifeCycle = new JavaObjectLifeCycle();
    }
}
