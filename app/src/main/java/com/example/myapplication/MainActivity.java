package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.widget.TextClock;
import android.widget.TimePicker;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    public String AlarmTime(){
        Integer alarmHours = timePicker.getCurrentHour();
        Integer alarmMinutes = timePicker.getCurrentMinute();
        String stringAlMinutes;

        if (alarmMinutes < 10){
            stringAlMinutes = "0";
            stringAlMinutes = stringAlMinutes.concat(alarmMinutes.toString());
        }else {
            stringAlMinutes = alarmMinutes.toString();
        }

        String alarmTime;

        if (alarmHours > 12){
            alarmHours = alarmHours - 12;
            alarmTime = alarmHours.toString().concat(":").concat(stringAlMinutes).concat(" PM");
        }else {
            alarmTime = alarmHours.toString().concat(":").concat(stringAlMinutes).concat(" AM");
        }

        return alarmTime;
    }

    TimePicker timePicker;
    TextClock textClock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timePicker = findViewById(R.id.timePic);
        textClock = findViewById(R.id.txtClock);
        textClock.getFormat12Hour();

        final Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (textClock.getText().toString().equals(AlarmTime())){
                    r.play();
                }else {
                    r.stop();
                }
            }
        }, 0, 1000);



    }

}