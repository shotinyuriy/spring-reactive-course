package edu.reactive.example;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class MonoExample {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> elements = new ArrayList<>();
        Mono.just(2)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
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
