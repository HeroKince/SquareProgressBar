package com.geekince.squareprogressbar.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.geekince.squareprogressbar.SquareProgressBar;

public class MainActivity extends AppCompatActivity {

    private SquareProgressBar squareProgress;
    private SquareProgressBar squareCornerProgress;
    private int progress = 0;
    private final Handler mHandler = new Handler(Looper.myLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        squareProgress = findViewById(R.id.squareProgressBar);
        squareProgress.setShouldShowBackground(true);

        squareCornerProgress = findViewById(R.id.squareCornerProgressBar);

        startProgress();
    }

    private void startProgress() {
        new Thread(() -> {
            while (progress < 100) {
                progress += 1;
                mHandler.post(() -> {

                    squareProgress.setProgress(progress);
                    squareCornerProgress.setProgress(progress);

                    if (progress == 100) {
                        progress = 0;
                        startProgress();
                    }
                });
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}