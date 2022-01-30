package learn.junit5.shopping.timeout;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
public class TimeoutExamples {

    @Timeout(value = 5)
    @Test
    void timeout_example() throws ExecutionException, InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        CompletableFuture completableFuture = CompletableFuture.runAsync(() -> {
            synchronized (this) {
                try {
                    this.wait(Duration.ofSeconds(60).toMillis());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        stopWatch.stop();
        log.info("Time spent: {}s", stopWatch.getTime());

        completableFuture.get();

    }
}
