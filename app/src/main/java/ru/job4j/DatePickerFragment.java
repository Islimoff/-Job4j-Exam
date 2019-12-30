package ru.job4j;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements OnDateSetListener{

    DatePickerListener datePickerListener;

    @Override
    public void onAttach(Context context) {
        try {
            super.onAttach(context);
            datePickerListener = (DatePickerListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(String.format("%s must implement DatePickerListener", context.toString()));
        }
    }

    public interface DatePickerListener {
        void onDateSet(DatePicker view, int year, int hourOfDay, int minute);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        datePickerListener.onDateSet(view, year, month, day);
    }
}
