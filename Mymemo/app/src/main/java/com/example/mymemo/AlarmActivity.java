package com.example.mymemo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity {
    AlarmManager alarm;
    TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm);

        setTitle("Alarm");


        Intent intent = getIntent();
        String result = intent.getStringExtra("from noti");

        if(result != null) Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT) .show();

        alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        timePicker=findViewById(R.id.tp_timepicker);

        Button b = findViewById(R.id.button);
        Button b2 = findViewById(R.id.button2);

        b.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) { regist(); }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { unregist(); }
        });



    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    void regist() {


        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
        calendar.set(Calendar.MINUTE, timePicker.getMinute());
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        int requestCode = (int)calendar.getTimeInMillis();


        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
        intent.putExtra("sendMessage", "메모알람");
        PendingIntent pIntent = PendingIntent.getBroadcast(getApplicationContext(),
                requestCode,
                intent,
                0);

        alarm.setExact(AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),pIntent);
        Toast.makeText(getApplicationContext(),"알람 등록됨", Toast.LENGTH_SHORT).show();
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    void unregist() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
        calendar.set (Calendar.MINUTE, timePicker.getMinute());
        calendar.set (Calendar.SECOND, 0);
        calendar.set (Calendar.MILLISECOND, 0) ;
        int requestCode = (int)calendar.getTimeInMillis();

        Intent intent = new Intent (  this, AlarmReceiver.class);
        PendingIntent pIntent = PendingIntent.getBroadcast(
                this,
                requestCode,
                intent,
                0);
        alarm.cancel(pIntent);
    }
}
