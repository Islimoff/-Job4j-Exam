package ru.job4j.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class ConfirmDeleteExamsListFragment extends DialogFragment {

    private  ConfirmDeleteExamsListener callback;

    public interface ConfirmDeleteExamsListener {
        void onPositiveDialogClick(DialogFragment dialog);
        void onNegativeDialogClick(DialogFragment dialog);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = new AlertDialog.Builder(getActivity())
                .setTitle("Удалить все?")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        callback.onPositiveDialogClick(ConfirmDeleteExamsListFragment.this);
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        callback.onNegativeDialogClick(ConfirmDeleteExamsListFragment.this);
                    }
                })
                .create();
        return dialog;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (ConfirmDeleteExamsListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(String.format("%s must implement ConfirmDeleteExamsListener", context.toString()));
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }
}
