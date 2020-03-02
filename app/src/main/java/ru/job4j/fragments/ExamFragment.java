package ru.job4j.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import ru.job4j.R;
import ru.job4j.data.OptionStore;
import ru.job4j.data.QuestionStore;
import ru.job4j.models.Option;
import ru.job4j.models.Question;

public class ExamFragment extends Fragment {

    private View view;
    private int position = 0;
    private List<Question> questions;
    private RadioGroup variants;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_exam, container, false);
        Bundle args = getArguments();
        questions = QuestionStore.getStore(getContext())
                .getByExamId(args.getInt("id"));
        fillForm();
        Button next = view.findViewById(R.id.next);
        next.setOnClickListener(this::nextBtn);
        Button hint = view.findViewById(R.id.hint);
        hint.setOnClickListener(this::hintButton);
        Button previous = view.findViewById(R.id.previous);
        previous.setOnClickListener(this::previousBtn);
        Button back = view.findViewById(R.id.back_to_list);
        back.setOnClickListener(this::backBtn);
        return view;
    }

    private void fillForm() {
        variants = view.findViewById(R.id.variants);
        TextView questionText = view.findViewById(R.id.question);
        Question question = questions.get(position);
        List<Option>options=OptionStore.getStore(getContext()).getByQuestionId(question.getId());
        questionText.setText(question.getText());
        for (int index = 0; index != variants.getChildCount(); index++) {
            RadioButton button = (RadioButton) variants.getChildAt(index);
            Option option = options.get(index);
            button.setId(option.getId());
            button.setText(option.getText());
        }
    }

    private void nextBtn(View view) {
        position++;
        fillForm();
    }

    private void previousBtn(View view) {
        position--;
        fillForm();
    }

    private void backBtn(View view) {
        getActivity().onBackPressed();
    }

    private void hintButton(View view) {
    }
}
