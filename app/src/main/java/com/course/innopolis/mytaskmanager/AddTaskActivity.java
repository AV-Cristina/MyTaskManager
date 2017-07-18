package com.course.innopolis.mytaskmanager;


import android.support.v7.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddTaskActivity extends AppCompatActivity {
    private TextView mSaveTask;
    private TextView mDateBeg;
    private TextView mTimeBeg;
    private TextView mDateEnd;
    private TextView mTimeEnd;
    private Switch mAllDay;
    private Context context;

    private void setDate(TextView mDateTextView) {
        final TextView mDate = mDateTextView;
        DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month += 1;
                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                Date date = null;
                try {
                    date = format.parse(dayOfMonth + "." + month + "." + year);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                SimpleDateFormat dateFormatter = new SimpleDateFormat("E, d MMMM yyyy 'г. '");
                mDate.setText(dateFormatter.format(date));
            }
        };
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        // если это не первый вызов, то в диалоге должна быть ранее установленная дата
        // а не текущая
        DatePickerDialog datePickerDialog = new DatePickerDialog(AddTaskActivity.this, dateListener, year, month, dayOfMonth);
        datePickerDialog.show();
    }


    private void setTime(TextView mTimeTextView) {
        final TextView mTime = mTimeTextView;
        TimePickerDialog.OnTimeSetListener timeListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hours, int minutes) {
                StringBuilder time = new StringBuilder(5);
                if (hours < 10) {
                    time.append("0");
                }
                time.append(hours).append(":");
                if (minutes < 10) {
                    time.append("0");
                }
                time.append(minutes);
                mTime.setText(time);
            }
        };
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(AddTaskActivity.this, timeListener, hour, minute, true);
        timePickerDialog.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        mTimeBeg = (TextView) findViewById(R.id.timeBeg);
        mDateBeg = (TextView) findViewById(R.id.dateBeg);
        mTimeEnd = (TextView) findViewById(R.id.timeEnd);
        mDateEnd = (TextView) findViewById(R.id.dateEnd);

        SimpleDateFormat dateFormatter = new SimpleDateFormat("E, d MMMM yyyy 'г. '");
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");

        Date dateBeg = new Date();
        long millis = dateBeg.getTime() + 3600000L;
        Date dateEnd = new Date(millis);

        mDateBeg.setText(dateFormatter.format(dateBeg));
        mTimeBeg.setText(timeFormatter.format(dateBeg));

        mDateEnd.setText(dateFormatter.format(dateEnd));
        mTimeEnd.setText(timeFormatter.format(dateEnd));

        mDateBeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDate((TextView) view);
            }
        });

        mTimeBeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTime((TextView) view);
            }
        });

        mDateEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDate((TextView) view);
            }
        });

        mTimeEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTime((TextView) view);
            }
        });

        mAllDay = (Switch) findViewById(R.id.allDayTask);

        mAllDay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    mTimeBeg.setVisibility(View.INVISIBLE);
                    mTimeEnd.setVisibility(View.INVISIBLE);
                } else {
                    mTimeBeg.setVisibility(View.VISIBLE);
                    mTimeEnd.setVisibility(View.VISIBLE);
                }
            }
        });

        /*mSaveTask = (TextView) findViewById(R.id.saveTask);
        mSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //охранение задачи в БД
            }
        });*/
    }
}