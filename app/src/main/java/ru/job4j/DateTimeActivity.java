package ru.job4j;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

public class DateTimeActivity extends AppCompatActivity implements DatePickerFragment.DatePickerListener,
        TimePickerFragment.TimePickerListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_time_layout);
        Button dateTime = findViewById(R.id.date_time_button);
        dateTime.setOnClickListener(this::dateTimeBtn);
    }

    private void dateTimeBtn(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        FragmentManager manager = getSupportFragmentManager();
        newFragment.show(manager, "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
//        TextView currentDateTime = view.findViewById(R.id.current_date_time);
//        currentDateTime.setText(day+"."+month+"."+year);
        DialogFragment timePickerFragment = new TimePickerFragment();
        timePickerFragment.setCancelable(false);
        timePickerFragment.show(getSupportFragmentManager(), "timePicker");
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView currentDateTime = view.findViewById(R.id.current_date_time);
        currentDateTime.setText(hourOfDay+"."+minute);
    }
}
