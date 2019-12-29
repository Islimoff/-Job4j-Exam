package ru.job4j;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.Calendar;
import java.util.Date;

public class DateTimeFragment extends Fragment {

    private View view;
    private static final int REQUEST_DATE = 0;
    private static final String DIALOG_DATE = "DialogDate";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.date_time_layout, container, false);
        setDate(new Date());
        Button dateTime = view.findViewById(R.id.date_time_button);
        dateTime.setOnClickListener(this::dateTimeBtn);
        return view;
    }

    private void setDate(Date date) {
        TextView currentDateTime = view.findViewById(R.id.current_date_time);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        currentDateTime.setText(calendar.get(Calendar.DAY_OF_MONTH)
                + "." + calendar.get(Calendar.MONTH)
                + "." + calendar.get(Calendar.YEAR));
    }

    private void dateTimeBtn(View view) {
        FragmentManager fragmentManager = getFragmentManager();
        DatePickerFragment dialog = new DatePickerFragment();
        dialog.setTargetFragment(DateTimeFragment.this, REQUEST_DATE);
        dialog.show(fragmentManager, DIALOG_DATE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            setDate(date);
        }
    }
}
