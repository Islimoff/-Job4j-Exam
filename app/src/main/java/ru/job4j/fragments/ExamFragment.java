package ru.job4j.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import ru.job4j.R;
import ru.job4j.activities.ResultActivity;
import ru.job4j.data.OptionStore;
import ru.job4j.data.QuestionStore;
import ru.job4j.data.Store;
import ru.job4j.models.Option;
import ru.job4j.models.Question;

public class ExamFragment extends Fragment {

    public static final String CORRECTANSWERS = "correctAnswers";
    public static final String WRONGANSWERS = "wrongAnswers";
    private View view;
    private int position = 0;
    private List<Question> questions;
    private List<Option> options;
    private RadioGroup variants;
    private Question question;
    private int correctAnswers;
    private int wrongAnswers;

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
        correctAnswers = 0;
        wrongAnswers = questions.size();
        return view;
    }

    private void fillForm() {
        variants = view.findViewById(R.id.variants);
        view.findViewById(R.id.previous).setEnabled(position != 0);
        TextView questionText = view.findViewById(R.id.question);
        question = questions.get(position);
        if(question.getAnswer()==0){
            variants.clearCheck();
        }else {
            variants.check(question.getAnswer());
        }
        options = OptionStore.getStore(getContext()).getByQuestionId(question.getId());
        questionText.setText(question.getText());
        for (int index = 0; index != variants.getChildCount(); index++) {
            RadioButton button = (RadioButton) variants.getChildAt(index);
            Option option = options.get(index);
            button.setId(index+1);
            button.setText(option.getText());
        }
        calculateResults();
    }

    private void nextBtn(View view) {
        question.setAnswerId(variants.getCheckedRadioButtonId());
        QuestionStore.getStore(getContext()).update(question);
        if (position == questions.size() - 1) {
            calculateResults();
            Intent intent = new Intent(getContext(), ResultActivity.class);
            intent.putExtra(CORRECTANSWERS, correctAnswers);
            intent.putExtra(WRONGANSWERS, wrongAnswers);
            startActivity(intent);
        } else {
            if (variants.getCheckedRadioButtonId() != -1) {
                showAnswer();
                position++;
                fillForm();
            } else {
                showWarning();
            }
        }
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

    private void showAnswer() {
        Toast.makeText(
                getContext(), "Your answer is " + variants.getCheckedRadioButtonId()
                        + ", correct is " + question.getAnswer(),
                Toast.LENGTH_SHORT
        ).show();
    }

    private void showWarning() {
        Toast.makeText(
                getContext(), "Select one of the options",
                Toast.LENGTH_SHORT
        ).show();
    }

    private void calculateResults() {
      int c= variants.getCheckedRadioButtonId();
            if (options.get(c-1).getCorrect()) {
                correctAnswers++;
                wrongAnswers--;
            }
    }
}
