package com.github.diegopacheco.sandbox.java.netflixoss.karyon.hystrix;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;

import rx.Observable;
import rx.Subscriber;

public class SimpleCommand extends HystrixObservableCommand<String> {
	
    private final String ops;

    public SimpleCommand(String ops) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.ops = ops;
    }

    @Override
    protected Observable<String> construct() {
        return Observable.create(observer -> {
            try {
                if (!observer.isUnsubscribed()) {
                    observer.onNext("Avaliable Ops : " + ops);
                    observer.onCompleted();
                }
            } catch (Exception e) {
                observer.onError(e);
            }
        });
    }
}