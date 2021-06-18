package edu.reactive.example;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class FluxSinkExample {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> elements = new ArrayList<>();
        Flux.<Integer>create(fluxSink -> {
            for (int i = 0; i < 10; i++) {
                if (i == 3) {
                    fluxSink.error(new RuntimeException("Test"));
                } else {
                    fluxSink.next(i);
                }
            }
        })
                .log()
                .subscribe(new Subscriber<Integer>() {
                    Subscription s;
                    int received = 0;
                    @Override
                    public void onSubscribe(Subscription s) {
                        this.s = s;
                        s.request(3);
                    }
                    @Override
                    public void onNext(Integer integer) {
                        elements.add(integer);
                        received++;
                        if (received >= 3) {
                            received = 0;
                            s.request(3);
                        }
                    }
                    @Override
                    public void onError(Throwable t) {
                        s.request(3);
                    }
                    @Override
                    public void onComplete() {
                    }
                });

        Thread.sleep(1000);
        System.out.println("elements are=" + elements);
    }
}
