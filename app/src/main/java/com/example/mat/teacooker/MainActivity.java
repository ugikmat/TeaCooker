package com.example.mat.teacooker;

import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.layout_fragment,new TeaListFragment(),"fragmentList");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


//        textCounter = findViewById(R.id.text_counter);
//        btnStart = findViewById(R.id.btn_start);
//
//        btnStart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                new CountDownTimer(6000, 1000) {
//                    @Override
//                    public void onTick(long l) {
//                        textCounter.setText(String.valueOf(l));
//
//                    }
//
//                    @Override
//                    public void onFinish() {
//                        textCounter.setText("FINISH");
//
//                    }
//                }.start();
//            }
//        });

    }
}
