package com.example.mat.teacooker;


import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mat.teacooker.model.Tea;

import static android.content.Context.NOTIFICATION_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
public class TimerFragment extends Fragment {

    private NotificationManager mNotifyManager;
    private NotificationCompat.Builder mNotifyBuilder;
    private Notification myNotification;
    private static final int NOTIFICATION_ID = 0001;

    TextView textTitle,textTime;

    Button btnCountdown;

    Thread coundownThread;

    public TimerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timer, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textTitle = getView().findViewById(R.id.tea_title);
        textTime = getView().findViewById(R.id.text_time);


        btnCountdown = getView().findViewById(R.id.btn_countdown);

        int time = getArguments().getInt("duration");
        final int minute = time/60000; //60000 in milisecond -> 60 detik
        final int second=(time%60000)/1000;

        textTitle.setText(getArguments().getString("name"));
        textTime.setText(minute+":"+second);


        btnCountdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (coundownThread == null || coundownThread.getState() == Thread.State.TERMINATED) {
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            int mins=minute;
                            int secs=second;
                            try {
                                for (int min = mins; min >=0; min--) {
                                    for(int sec=secs;sec>=0;sec--){
                                        // Simulating something timeconsuming
                                        Thread.sleep(1000); // in milisecond
                                        final int finalSec = sec;
                                        final int finalMin = min;
                                        textTime.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                textTime.setText(finalMin +":"+ finalSec);
                                            }
                                        });
                                    }
                                    secs=59;
                                }
                                showNotif();
                                textTime.setText(minute+":"+second);
                                coundownThread.interrupt();

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    coundownThread = new Thread(runnable);
                    coundownThread.start();
            }else{
                    textTime.setText(minute+":"+second);
                    coundownThread.interrupt();
                }
        }});

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mNotifyManager = (NotificationManager)getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public void showNotif(){
        mNotifyBuilder = new NotificationCompat.Builder(getActivity())
                .setContentTitle("Peringatan!")
                .setContentText("Teh Sudah Siap dinikmati")
                .setSmallIcon(R.mipmap.ic_launcher_round);
        //Vibration
        mNotifyBuilder.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 });

        myNotification = mNotifyBuilder.build();

//        mNotifyManager.notify();
        mNotifyManager.notify(NOTIFICATION_ID, myNotification);

    }
}
