package com.example.sundemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.text.format.Time;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    LinearLayout linear;
    TextView txt_time;
    private sunDemo myView;
    int hour;
    int min ;
    private int count = 0;
    private boolean isMove = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_time = findViewById(R.id.txt_time);
        linear = findViewById(R.id.linear);
        myView = new sunDemo(this);
        linear.addView(myView);
        initCircle();

        txt_time.setText(hour+":"+min);
    }

    private void initCircle() {

        Time now = new Time();
        now.setToNow();
        min = now.minute;

        if (now.hour == 0)
            hour = 24;
        else
            hour = now.hour;
        double s = (double) hour / 24;
        double d = s * 100;
        double c = Math.ceil(d);

        Handler handler = new Handler();

        final Runnable r = new Runnable() {
            public void run() {
                if (isMove) {
                    myView.illuminate(count);
                    count = count + 1;
                    handler.postDelayed(this, 15);
                    if (count == c)
                        isMove = false;
                }


            }
        };

        handler.postDelayed(r, 15);

    }
}