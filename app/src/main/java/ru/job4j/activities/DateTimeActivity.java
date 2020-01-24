package ru.job4j.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import ru.job4j.fragments.DatePickerFragment;
import ru.job4j.R;
import ru.job4j.fragments.TimePickerFragment;

public class DateTimeActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener {

    private TextView currentDateTime;
    private String date;
    private String time;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_time_layout);
        Button dateTime = findViewById(R.id.date_time_button);
        currentDateTime = findViewById(R.id.current_date_time);
        dateTime.setOnClickListener(this::dateTimeBtn);
    }

    private void dateTimeBtn(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        date="Date " + day + "." + month + "." + year + "\n";
        DialogFragment timePickerFragment = new TimePickerFragment();
        timePickerFragment.setCancelable(false);
        timePickerFragment.show(getSupportFragmentManager(), "timePicker");
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        time="Time " + hourOfDay + "." + minute;
        currentDateTime.setText(date+time);
    }
}
