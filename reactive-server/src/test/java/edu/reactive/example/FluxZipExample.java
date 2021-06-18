package edu.reactive.example;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class FluxZipExample {
    public static void main(String[] args) throws InterruptedException {
        var letters = Flux.fromArray("abcdefghijklmnopqrstuvwxyz".split(""))
                .map(String::toUpperCase);

        var numbers = Flux.range(1, 26)
                .zipWith(letters, (number, letter) -> number + letter)
                .subscribe(System.out::println);

    }
}
