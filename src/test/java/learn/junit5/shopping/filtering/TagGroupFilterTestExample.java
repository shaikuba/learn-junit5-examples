package learn.junit5.shopping.filtering;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TagGroupFilterTestExample {

    @TagGroup.ApiTest
    void filtering_three() {
    }

    @TagGroup.PerformanceTest
    void filtering_four() {
    }

    @TagGroup.PerformanceApiTest
    void filtering_five() {
    }

}
