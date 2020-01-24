package ru.job4j.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.job4j.R;
import ru.job4j.activities.ExamActivity;

public class ResultFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.result_activity, container, false);
        final TextView text = view.findViewById(R.id.answers);
        Bundle arguments = getArguments();
        int correctAnswers = arguments.getInt(ExamActivity.CORRECTANSWERS, 0);
        int wrongAnswers = arguments.getInt(ExamActivity.WRONGANSWERS, 0);
        text.setText("Correct Answers: " + correctAnswers + "\nWrong Answers: " + wrongAnswers);
        return view;
    }

    public static ResultFragment of(int correctAnswers, int wrongAnswers) {
        ResultFragment resultFragment = new ResultFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ExamActivity.CORRECTANSWERS, correctAnswers);
        bundle.putInt(ExamActivity.WRONGANSWERS, wrongAnswers);
        resultFragment.setArguments(bundle);
        return resultFragment;
    }
}
