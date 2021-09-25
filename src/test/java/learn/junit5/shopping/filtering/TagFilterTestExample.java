package learn.junit5.shopping.filtering;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

@Slf4j
@DisplayName("A filtering test example.")
public class TagFilterTestExample {

    @DisplayName("filtering test one.")
    @Test
    void filtering_one() {
    }

    @DisplayName("filtering test two.")
    @Test
    void filtering_two() {
    }

    @Tag("api")
    @Test
    void filtering_three() {
    }

    @Tag("performance")
    @Test
    void filtering_four() {
    }

    @Tag("api")
    @Tag("performance")
    @Test
    void filtering_five() {
    }


    @Tags(value = {@Tag("performance"), @Tag("security")})
    @Test
    void filtering_six() {
    }
}
