package com.ioextendedgr.rxjava.example;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private Button btnEmitSingleValue;
    private Button btnClear;
    private Button btnCalculateValue;
    private TextView txtValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEmitSingleValue = (Button) findViewById(R.id.btnEmitSingleValue);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnCalculateValue = (Button) findViewById(R.id.btnCalculateValue);
        txtValue = (TextView) findViewById(R.id.txtValue);

        btnEmitSingleValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emitSingleValue();
            }
        });

        btnCalculateValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateValue();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTxtValue("");
            }
        });
    }

    private void calculateValue() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                Log.i(TAG, "starting call()");
                try {
                    subscriber.onNext(100 + 100);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted() called");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError() called");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.i(TAG, "onNext() called");
                        updateTxtValue(integer);
                    }
                });
    }

    private void emitSingleValue() {
        Observable<Integer> observable = Observable
                .just(Integer.valueOf(1));

        observable
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer currInteger) {
                        Log.i(TAG, "emitSingleValue()->updateTxtValue()");
                        updateTxtValue(currInteger);
                    }
                });

//        showing that you don't have to recreate the observable, you can use
//        the same one over and over again
        observable
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer currInteger) {
                        Log.i(TAG, "getting the integer again: " + currInteger);
                    }
                });
    }

    private Observable<Integer> createNumberObservable() {
        return Observable.from(Arrays.asList(1, 2, 3, 4, 5));

    }

    public void updateTxtValue(Object value) {
        txtValue.setText(value.toString());
    }


}
