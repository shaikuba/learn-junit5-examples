package junit.learning.lifecycle;

public class TestObj {

    static String greetMsg = "hello";

    static {
        greetMsg = "hello, person";

        System.out.println(greetMsg);
    }

    private String name = "ray";

    {
        System.out.println(name);
    }


    public TestObj() {
        System.out.println("constructor init");
    }


    public static void main(String[] args) {
        new TestObj();
    }
}
