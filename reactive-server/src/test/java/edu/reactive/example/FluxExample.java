package edu.reactive.example;

import reactor.core.publisher.Flux;

public class FluxExample {
    public static void main(String[] args) throws InterruptedException {

        Flux.just(1, 2, 3)
                .filter(i -> i != -1)
                .map(i -> i * 3)
                .skip(1)
                .take(1)
                .subscribe();
    }
}
