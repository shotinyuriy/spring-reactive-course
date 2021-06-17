package edu.reactive.example;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HotExample {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> elements = new ArrayList<>();
        Flux<Long> hot = Flux.interval(Duration.ofMillis(10));

        hot.map(index -> (int) (index - 10))
                .subscribe(elements::add);

        hot.filter(index -> index % 3 == 0)
                .subscribe(index -> System.out.print("[" + index + "]"));

        hot.filter(index -> index % 5 == 0)
                .subscribe(index -> System.out.print("(" + index + ")"));

        Thread.sleep(200);
        System.out.println("\nelements are=" + elements);
    }
}
