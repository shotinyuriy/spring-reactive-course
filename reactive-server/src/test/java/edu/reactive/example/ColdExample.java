package edu.reactive.example;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ColdExample {
    public static void main(String[] args) throws InterruptedException {
        List<Long> elements1 = new ArrayList<>();
        List<Long> elements2 = new ArrayList<>();
        List<Long> elements3 = new ArrayList<>();
        Flux<Long> hot = Flux.interval(Duration.ofMillis(10));

        hot.subscribe(elements1::add);
        Thread.sleep(50);
        hot.subscribe(elements2::add);
        Thread.sleep(50);
        hot.subscribe(elements3::add);
        Thread.sleep(50);
        System.out.println("\nelements1 are=" + elements1+"\nelements2 are=" + elements2+"\nelements3 are=" + elements3);
    }
}
