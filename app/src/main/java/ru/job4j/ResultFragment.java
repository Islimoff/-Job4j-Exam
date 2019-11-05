package ru.job4j;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ResultFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.result_activity, container, false);
        Bundle arguments = getActivity().getIntent().getExtras();
        final TextView text = view.findViewById(R.id.answers);
        int correctAnswers = arguments.getInt("correctAnswers");
        int wrongAnswers = arguments.getInt("wrongAnswers");
        text.setText("Correct Answers: " + correctAnswers + "\nWrong Answers: " + wrongAnswers);
        return view;
    }
}
