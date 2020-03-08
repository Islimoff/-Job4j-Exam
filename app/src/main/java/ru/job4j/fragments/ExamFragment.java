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
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import java.util.List;

import ru.job4j.R;
import ru.job4j.activities.HintActivity;
import ru.job4j.activities.ResultActivity;
import ru.job4j.data.ExamStore;
import ru.job4j.data.OptionStore;
import ru.job4j.data.QuestionStore;
import ru.job4j.models.Exam;
import ru.job4j.models.Option;
import ru.job4j.models.Question;

public class ExamFragment extends Fragment {

    public static final String CORRECTANSWERS = "correctAnswers";
    public static final String HINT_FOR = "hint_for";
    public static final String QUESTION = "question";
    private View view;
    private int position = 0;
    private List<Question> questions;
    private List<Option> options;
    private RadioGroup variants;
    private Question question;
    private boolean answers[];
    private int examId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_exam, container, false);
        Bundle args = getArguments();
        examId = args.getInt("id");
        questions = QuestionStore.getStore(getContext())
                .getByExamId(examId);
        fillForm();
        Button next = view.findViewById(R.id.next);
        next.setOnClickListener(this::nextBtn);
        Button hint = view.findViewById(R.id.hint);
        hint.setOnClickListener(this::hintButton);
        Button previous = view.findViewById(R.id.previous);
        previous.setOnClickListener(this::previousBtn);
        Button back = view.findViewById(R.id.back_to_list);
        back.setOnClickListener(this::backBtn);
        answers = new boolean[questions.size()];
        return view;
    }

    private void fillForm() {
        variants = view.findViewById(R.id.variants);
        variants.clearCheck();
        view.findViewById(R.id.previous).setEnabled(position != 0);
        TextView questionText = view.findViewById(R.id.question);
        question = questions.get(position);
        options = OptionStore.getStore(getContext()).getByQuestionId(question.getId());
        questionText.setText(question.getText());
        for (int index = 0; index != variants.getChildCount(); index++) {
            RadioButton button = (RadioButton) variants.getChildAt(index);
            Option option = options.get(index);
            button.setId(index + 1);
            button.setText(option.getText());
            if (index + 1 == question.getAnswer()) {
                button.setChecked(true);
            }
        }
    }

    private void nextBtn(View view) {
        question.setAnswerId(variants.getCheckedRadioButtonId());
        QuestionStore.getStore(getContext()).update(question);
        if (variants.getCheckedRadioButtonId() != -1) {
            checkAnswer();
            if (position == questions.size() - 1) {
                int result = calculateResults();
                Exam exam = ExamStore.getStore(getContext()).getById(examId);
                exam.setResult(result);
                ExamStore.getStore(getContext()).update(exam);
                Intent intent = new Intent(getContext(), ResultActivity.class);
                intent.putExtra(CORRECTANSWERS, calculateResults());
                startActivity(intent);
            } else {
                position++;
                fillForm();
            }
        } else {
            showWarning();
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
        Intent intent = new Intent(getContext(), HintActivity.class);
        intent.putExtra(HINT_FOR, getCorrectAnswer());
        intent.putExtra(QUESTION, String.valueOf(question.getText()));
        startActivity(intent);
    }

    private void showWarning() {
        Toast.makeText(
                getContext(), "Select one of the options",
                Toast.LENGTH_SHORT
        ).show();
    }

    private void checkAnswer() {
        int checkedRadioButtonId = variants.getCheckedRadioButtonId();
        answers[position] = options.get(checkedRadioButtonId - 1).getCorrect();
    }

    private int calculateResults() {
        float correctAnswers = 0;
        for (int i = 0; i != questions.size(); i++) {
            if (answers[i]) correctAnswers++;
        }
        return (int) (correctAnswers / (float) questions.size() * 100);
    }

    private int getCorrectAnswer() {
        int correctAnswer = 0;
        for (int i = 0; i != options.size(); i++) {
            if (options.get(i).getCorrect()) {
                correctAnswer = i + 1;
            }
        }
        return correctAnswer;
    }

    public static ExamFragment of(int id) {
        ExamFragment examFragment = new ExamFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(StartExamFragment.EXAM_ID, id);
        examFragment.setArguments(bundle);
        return examFragment;
    }
}
