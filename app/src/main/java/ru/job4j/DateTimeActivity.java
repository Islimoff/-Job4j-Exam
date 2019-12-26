package ru.job4j;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import java.util.Calendar;
import java.util.Date;

public class DateTimeActivity extends AppCompatActivity {

    private static final String DIALOG_DATE = "DialogDate";

    @Override
    protected void onCreate(@Nullable Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.date_time_activity);
        setDate(new Date());
        Button dateTime = findViewById(R.id.date_time_button);
        dateTime.setOnClickListener(this::dateTimeBtn);
    }

    private void setDate(Date date) {
        TextView currentDateTime=findViewById(R.id.current_date_time);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        currentDateTime.setText(calendar.get(Calendar.DAY_OF_MONTH)
                +"."+calendar.get(Calendar.MONTH)
                +"." +calendar.get(Calendar.YEAR));
    }

    private void dateTimeBtn(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        DatePickerDialog dialog = new DatePickerDialog();
        dialog.show(fragmentManager, DIALOG_DATE);
    }
}
