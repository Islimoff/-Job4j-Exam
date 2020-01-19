package ru.job4j;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.HashMap;
import java.util.Map;

public class    HintFragment extends Fragment {

    private final Map<Integer, String> answers = new HashMap<Integer, String>();

    public HintFragment() {
        this.answers.put(0, "Hint 1");
        this.answers.put(1, "Hint 2");
        this.answers.put(2, "Hint 3");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.hint_activity, container, false);
        final TextView question = view.findViewById(R.id.question);
        final TextView answer = view.findViewById(R.id.hint);
        Bundle arguments = getArguments();
        int page = arguments.getInt(ExamActivity.HINT_FOR, 0);
        String questionText = arguments.getString(ExamActivity.QUESTION);
        question.setText(questionText);
        answer.setText(this.answers.get(page));
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
        bundle.putInt(ExamActivity.HINT_FOR, index);
        bundle.putString(ExamActivity.QUESTION, text);
        hint.setArguments(bundle);
        return hint;
    }
}
