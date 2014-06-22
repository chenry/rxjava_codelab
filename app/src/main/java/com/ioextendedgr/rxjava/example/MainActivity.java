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
import rx.functions.Action1;


public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private Button btnEmitSingleValue;
    private Button btnClear;
    private TextView txtValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEmitSingleValue = (Button) findViewById(R.id.btnEmitSingleValue);
        btnClear = (Button) findViewById(R.id.btnClear);
        txtValue = (TextView) findViewById(R.id.txtValue);

        btnEmitSingleValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emitSingleValue();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTxtValue("");
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
