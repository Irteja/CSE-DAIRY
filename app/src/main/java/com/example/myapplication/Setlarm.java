package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class Setlarm extends AppCompatActivity implements View.OnClickListener {
    private EditText course_name;
    private Button selectbtn, setbtn;
    private CheckBox sun, mon, tues, wed, thrus, every;
    private TimePickerDialog tpd;
    private String hour, minit, cname;
    private databaselite db;
    private int timeset = 0;
    private String tym;
    private AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setlarm);

        createNotificationChannel();


        course_name = findViewById(R.id.editcourse);
        selectbtn = findViewById(R.id.select_time);
        setbtn = findViewById(R.id.set);
        sun = findViewById(R.id.sunday);
        mon = findViewById(R.id.monday);
        tues = findViewById(R.id.tuesday);
        wed = findViewById(R.id.wednesday);
        thrus = findViewById(R.id.thrusday);
//        every = findViewById(R.id.everyday);
        setbtn.setOnClickListener(this);
        selectbtn.setOnClickListener(this);
        db = new databaselite(this);
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.select_time) {
            TimePicker tp = new TimePicker(this);
            int hr = tp.getCurrentHour();
            int min = tp.getCurrentMinute();
            tpd = new TimePickerDialog(Setlarm.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int i, int i1) {
                    hour = i + "";
                    minit = i1 + "";

                    if (i < 10) hour = "0" + i;
                    if (i1 < 10) minit = "0" + i1;
                    timeset += 1;
                    String h, m;
                    String n = "" + i1;
                    if (i == 0) {
                        h = "12";
                        m = "AM";
                    } else if (i < 12) {
                        if (i / 10 == 0) h = "0" + i;
                        else h = i + "";
                        m = "AM";
                    } else if (i == 12) {
                        h = "12";
                        m = "PM";
                    } else {
                        h = (i - 12) + "";
                        m = "PM";
                        if (i > 12) i -= 12;
                        if (i / 10 == 0) h = "0" + i;
                    }
                    if (i1 / 10 == 0) n = "0" + i1;
                    tym = " " + h + ":" + n + " " + m + " ";

                }
            }, hr, min, false);
            tpd.show();
        } else if (view.getId() == R.id.set) {
            ArrayList<String> list = new ArrayList<String>();
//            if (every.isChecked()) {
//                String value = sun.getText().toString();
//                list.add(value);
//                value = mon.getText().toString();
//                list.add(value);
//                value = tues.getText().toString();
//                list.add(value);
//                value = wed.getText().toString();
//                list.add(value);
//                value = thrus.getText().toString();
//                list.add(value);
//            } else
                if (sun.isChecked()) {
                String value = sun.getText().toString();
                list.add(value);
            } else if (mon.isChecked()) {
                String value = mon.getText().toString();
                list.add(value);
            } else if (tues.isChecked()) {
                String value = tues.getText().toString();

                list.add(value);
            } else if (wed.isChecked()) {
                String value = wed.getText().toString();

                list.add(value);
            } else if (thrus.isChecked()) {
                String value = thrus.getText().toString();
                list.add(value);
            }
            if (list.size() == 0)
                Toast.makeText(this, "Please enter the day", Toast.LENGTH_SHORT).show();
            else {

                cname = course_name.getText().toString();
                for (int i = 0; i < list.size(); i++) {
                    String d = list.get(i);

//                    long rowid = db.insertData(cname, hour + ":" + minit, d);
//                    if (rowid > 0) {
//                        Toast.makeText(getApplicationContext(), rowid + " is Successfully inserted", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(getApplicationContext(), "An error occurred", Toast.LENGTH_SHORT).show();
//                    }
                    Intent intent = new Intent(this, MyBroadCastReceiver.class);
                    intent.putExtra("ClassName", cname);

                    PendingIntent pi = PendingIntent.getBroadcast(this,0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    int h = Integer.parseInt(hour);
                    int m = Integer.parseInt(minit);
                    int daysUntil=0;

                    switch(d) {
                        case "Sunday":
                            daysUntil = (Calendar.SUNDAY - calendar.get(Calendar.DAY_OF_WEEK) + 7) % 7;
                            break;
                        case "Monday":
                            daysUntil = (Calendar.MONDAY - calendar.get(Calendar.DAY_OF_WEEK) + 7) % 7;
                            break;
                        case "Tuesday":
                            daysUntil = (Calendar.TUESDAY - calendar.get(Calendar.DAY_OF_WEEK) + 7) % 7;
                            break;
                        case "Wednesday":
                            daysUntil = (Calendar.WEDNESDAY - calendar.get(Calendar.DAY_OF_WEEK) + 7) % 7;
                            break;
                        case "Thursday":
                            daysUntil = (Calendar.THURSDAY - calendar.get(Calendar.DAY_OF_WEEK) + 7) % 7;
                            break;
                        default:
                            // handle unexpected input
                    }
                    calendar.add(Calendar.DAY_OF_WEEK, daysUntil);
                    calendar.set(Calendar.HOUR_OF_DAY, h);
                    calendar.set(Calendar.MINUTE, m);
                    calendar.set(Calendar.SECOND, 0);
                    alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    long alarmTime = calendar.getTimeInMillis();
                    alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);


                }
            }
        }
        Toast.makeText(this, "The Alarm is set.", Toast.LENGTH_SHORT).show();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("default", "Default Channel", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notificationManager = (NotificationManager)
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}