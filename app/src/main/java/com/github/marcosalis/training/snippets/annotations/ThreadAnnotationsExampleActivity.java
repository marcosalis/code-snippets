package com.github.marcosalis.training.snippets.annotations;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.AnyThread;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import android.support.v7.app.AppCompatActivity;

/**
 * Example of use for the threading annotations in android-support-annotations.
 */
public class ThreadAnnotationsExampleActivity extends AppCompatActivity {

    private final HandlerThread mHandlerThread = new HandlerThread("myHandlerThread");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        doSomeStuff();
        manipulateUI();
        startHeavyComputation();
    }

    @UiThread
    private void manipulateUI() {
        // this should ONLY be called from the UI thread

        startHeavyComputation();

        mHandlerThread.start();
        new Handler(mHandlerThread.getLooper()).post(new Runnable() {
            @Override public void run() {
                startHeavyComputation();
            }
        });
    }

    @WorkerThread
    private void startHeavyComputation() {
        // this should not be called from the main thread!
        doSomeStuff();
        manipulateUIAfterComputation();
    }

    @UiThread
    private void manipulateUIAfterComputation() {
        // this should ONLY be called from the UI thread
    }

    @AnyThread
    private void doSomeStuff() {
        // this method doesn't access the activity state, and can't be executed from any thread
    }

}