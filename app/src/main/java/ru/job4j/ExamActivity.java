package ru.job4j;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class ExamActivity extends AppCompatActivity {
    private static final String TAG = "ExamActivity";
    private int currentBillTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            currentBillTotal = 0;
        } else {
            currentBillTotal = savedInstanceState.getInt("BILL_TOTAL");
            currentBillTotal++;
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_layout);
        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("BILL_TOTAL", currentBillTotal);
        Log.d(TAG, "current bill is " + currentBillTotal);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}
