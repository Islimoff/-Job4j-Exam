package ru.job4j;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class ConfirmHintDialogFragment extends DialogFragment {

    private ConfirmHintDialogListener callback;

    public interface ConfirmHintDialogListener {
        void onPositiveDialogClick(DialogFragment dialog);
        void onNegativeDialogClick(DialogFragment dialog);
    }

    @Override

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = new AlertDialog.Builder(getActivity())
                .setMessage("Показать подсказку?")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        callback.onPositiveDialogClick(ConfirmHintDialogFragment.this);
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        callback.onNegativeDialogClick(ConfirmHintDialogFragment.this);
                    }
                })
                .create();
        return dialog;
    }

    @Override

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (ConfirmHintDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(String.format("%s must implement ConfirmHintDialogListener", context.toString()));
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }
}
