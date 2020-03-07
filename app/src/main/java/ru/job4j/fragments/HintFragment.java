package ru.job4j.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.job4j.R;
import ru.job4j.activities.ExamActivity;

public class    HintFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.hint_activity, container, false);
        final TextView question = view.findViewById(R.id.question);
        final TextView answer = view.findViewById(R.id.hint);
        Bundle arguments = getArguments();
        int correctAnswer = arguments.getInt(ExamFragment.HINT_FOR, 0);
        String questionText = arguments.getString(ExamFragment.QUESTION);
        question.setText(questionText);
        answer.setText(correctAnswer);
        Button back = view.findViewById(R.id.back);
        back.setOnClickListener(this::backBtn);
        return view;
    }

    private void backBtn(View view) {
        getActivity().onBackPressed();
    }

    public static HintFragment of(int index, String text) {
        HintFragment hint = new HintFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ExamFragment.HINT_FOR, index);
        bundle.putString(ExamFragment.QUESTION, text);
        hint.setArguments(bundle);
        return hint;
    }
}
