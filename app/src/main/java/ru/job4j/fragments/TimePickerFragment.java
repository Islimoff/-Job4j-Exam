package ru.job4j.fragments;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment {

//    TimePickerListener timePickerListener;
//
//    @Override
//    public void onAttach(Context context) {
//        try {
//            super.onAttach(context);
//            timePickerListener = (TimePickerListener) context;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(String.format("%s must implement TimePickerListener", context.toString()));
//        }
//    }
//
//    public void onDetach() {
//        super.onDetach();
//        timePickerListener = null;
//    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), (OnTimeSetListener)getActivity(), hour, minute, DateFormat.is24HourFormat(getContext()));
    }
}
