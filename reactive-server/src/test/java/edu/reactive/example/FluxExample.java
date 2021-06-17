package edu.reactive.example;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class FluxExample {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> elements = new ArrayList<>();
        Flux.range(1, 100)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(10);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        elements.add(integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
        Thread.sleep(1);
        System.out.println("elements are="+elements);
    }
}
