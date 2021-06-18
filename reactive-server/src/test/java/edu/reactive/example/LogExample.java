package edu.reactive.example;

import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class LogExample {
    public static void main(String[] args) throws InterruptedException {
        successLog();
        errorLog();
        continueOnErrorLog();
    }

    private static void successLog() throws InterruptedException {
        List<Integer> elements = new ArrayList<>();
        Flux.just(1, 2, 3, 4)
                .log()
                .limitRate(3, 2)
                .subscribe(elements::add);
        Thread.sleep(10);
        System.out.println("elements are=" + elements);
        System.out.println();
    }

    private static void errorLog() throws InterruptedException {
        List<Integer> elements = new ArrayList<>();
        Flux.just(1, 2, 3, 4)
                .filter(i -> 100 / (3-i) != -1).log()
                .subscribe(elements::add);
        Thread.sleep(10);
        System.out.println("elements are=" + elements);
        System.out.println();
    }

    private static void continueOnErrorLog() throws InterruptedException {
        var elements = new ArrayList<>();
        Flux.just(1, 2, 3, 4, 5, 6, 7)
                .filter(i -> 100 / (3-i) != -1).onErrorContinue((ex, res) -> {})
                .limitRate(4, 3).onErrorContinue((ex, res) -> {})
                .subscribe(elements::add);
        Thread.sleep(100);
        System.out.println("elements are=" + elements);
        System.out.println();
    }
}
