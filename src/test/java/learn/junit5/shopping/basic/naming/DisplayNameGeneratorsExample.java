package learn.junit5.shopping.basic.naming;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

//@DisplayNameGeneration(value = DisplayNameGenerator.Standard.class)
//@DisplayNameGeneration(value = DisplayNameGenerator.Simple.class)
//@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayNameGeneration(value = DisplayNameGenerator.IndicativeSentences.class)
@Slf4j
public class DisplayNameGeneratorsExample {

    @Test
    void test_one(TestInfo testInfo) {
        log.info(testInfo.getDisplayName());
    }

    @Test
    void test_two() {
    }

}
