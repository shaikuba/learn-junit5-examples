package learn.junit5.shopping.basic.lifecycle;

public class MainA {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        System.out.println("main method");

        MainA.class.getClassLoader()
                .loadClass("learn.junit5.shopping.basic.lifecycle.JavaObjectLifeCycle")
                .newInstance();
    }
}
