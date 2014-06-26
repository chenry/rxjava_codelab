package com.ioextendedgr.rxjava.example.controller;

import android.util.Log;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by carlushenry on 6/23/14.
 */
public class ExampleController {

    public Observable<Integer> createSingleValueObservable() {
        return Observable.just(1);
    }

    public Observable<Integer> calculateValue() {
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                try {
                    subscriber.onNext(100 + 100);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });

    }
}
