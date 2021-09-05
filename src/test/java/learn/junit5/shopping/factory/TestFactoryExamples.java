package learn.junit5.shopping.factory;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit5.learning.example.shopping.ShoppingConsole;
import org.junit5.learning.example.shopping.model.Cart;
import org.junit5.learning.example.shopping.model.Goods;
import org.junit5.learning.example.shopping.service.CartService;
import org.junit5.learning.example.shopping.service.impl.CartServiceImpl;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.junit.jupiter.api.DynamicTest.stream;

/**
 * A @TestFactory method must return a single DynamicNode or a Stream, Collection, Iterable, Iterator, or array of
 * DynamicNode instances.
 * <p>
 * <p/>
 */
@Slf4j
public class TestFactoryExamples {

    private ShoppingConsole shoppingConsole;

    private Cart cart;

    private CartService cartService;

    private final Goods goods = Goods.newGoods("huawei", BigDecimal.valueOf(1200.0));

    @BeforeEach
    void setup() {
        log.info("init object in beforeEach");
        cartService = new CartServiceImpl();
        shoppingConsole = new ShoppingConsole();
        shoppingConsole.setCartService(cartService);

        cart = new Cart();
        Map<Goods, Integer> goodsMap = new HashMap<>();
        goodsMap.put(goods, 100);
        cart.setGoods(goodsMap);

    }

    // ---------------------------//
    // TestFactory Examples start //
    // ---------------------------//

    @TestFactory
    DynamicTest dynamicTestSingle() {
        return dynamicTest("1st dynamic test", () -> {
            shoppingConsole.removeGoods(cart, goods, 10);
            assertEquals(90, cart.getGoods().get(goods));
        });

    }

    @TestFactory
    Collection<DynamicTest> dynamicTestList() {
        return Arrays.asList(
                dynamicTest("1st dynamic test", () -> {
                    shoppingConsole.removeGoods(cart, goods, 10);

                    assertEquals(90, cart.getGoods().get(goods));
                })
                , dynamicTest("2nd dynamic test", () -> assertEquals(2, 2))
        );

    }

    @TestFactory
    DynamicTest[] dynamicTestArray() {

        return new DynamicTest[]{
                dynamicTest("1st dynamic test", () -> {
                    shoppingConsole.removeGoods(cart, goods, 10);

                    assertEquals(90, cart.getGoods().get(goods));
                })
                , dynamicTest("2nd dynamic test", () -> assertEquals(2, 2))
        };

    }

    /**
     * >>>>>>>>>>>>>Important<<<<<<<<<<<<
     *
     * @return
     */
    @TestFactory
    Stream<DynamicTest> dynamicTestStream() {
        return Stream.of(10, 20, 30).map(amount -> dynamicTest("1st dynamic test", () -> {
            int remainder = cart.getGoods().get(goods);
            shoppingConsole.removeGoods(cart, goods, amount);
            assertEquals(remainder - amount, cart.getGoods().get(goods));
        }));

    }

    @TestFactory
    Stream<DynamicTest> dynamicTestUriStream() {
        return Stream.of(10, 20, 30).map(amount -> dynamicTest("1st dynamic test", new File("/").toURI(), () -> {
            int remainder = cart.getGoods().get(goods);
            shoppingConsole.removeGoods(cart, goods, amount);
            assertEquals(remainder - amount, cart.getGoods().get(goods));
        }));

    }

    @TestFactory
    Stream<DynamicTest> dynamicTestInputGenerator() {

        return stream(Stream.of(10, 20, 30).iterator(), amount -> String.format("Test input amount %d", amount), amount -> {
            int remainder = cart.getGoods().get(goods);
            shoppingConsole.removeGoods(cart, goods, amount);
            assertEquals(remainder - amount, cart.getGoods().get(goods));
        });
    }

    @TestFactory
    Stream<DynamicNode> dynamicTestContainer() {

        return Stream.of(10, 20, 30)
                .map(amount -> dynamicContainer("Container"
                        , Stream.of(dynamicTest(String.format("Remove amount %d", amount), () -> assertEquals(1, amount))
                                , dynamicContainer("Nested Container"
                                        , Stream.of(dynamicTest(String.format("Remove amount %d", amount), () -> assertEquals(1, amount))))
                        )));
    }

}
