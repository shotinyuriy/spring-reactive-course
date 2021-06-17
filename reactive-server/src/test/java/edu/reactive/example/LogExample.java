package edu.reactive.example;

import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class LogExample {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> elements = new ArrayList<>();
        Flux.just(1, 2, 3, 4)
                .map(x -> 2 * x)
                .log()
                .subscribe(elements::add);
        System.out.println("elements are="+elements);
    }
}
