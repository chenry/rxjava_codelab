package com.ioextendedgr.rxjava.example;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;

import rx.Observable;


public class MainActivity extends Activity {

    private Button btnEmitSingleValue;
    private Button btnClear;
    private TextView txtValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Observable<Integer> numberObservable = createNumberObservable();

        btnEmitSingleValue = (Button) findViewById(R.id.btnEmitSingleValue);
        btnClear = (Button) findViewById(R.id.btnClear);
        txtValue = (TextView) findViewById(R.id.txtValue);

        btnEmitSingleValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTxtValue("100");
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTxtValue("");
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
