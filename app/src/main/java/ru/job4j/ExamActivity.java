package ru.job4j;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ExamActivity extends AppCompatActivity {

    private static final String TAG = "ExamActivity";
    private int currentBillTotal;
    private int position = 0;
    public static final String HINT_FOR = "hint_for";
    public static final String QUESTION = "question";
    private final List<Question> questions = questionsGenerator();
    private int answers[] = new int[questions.size()];
    int correctAnswers;
    int wrongAnswers=questions.size();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            currentBillTotal = 0;
        } else {
            answers = savedInstanceState.getIntArray("ANSWERS_ARRAY");
            position = savedInstanceState.getInt("POSITION");
            currentBillTotal = savedInstanceState.getInt("BILL_TOTAL");
            currentBillTotal++;
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        fillForm();
        Button next = findViewById(R.id.next);
        next.setOnClickListener(this::nextBtn);
        Button hint = findViewById(R.id.hint);
        hint.setOnClickListener(this::hintButton);
        Button previous = findViewById(R.id.previous);
        previous.setOnClickListener(this::previousBtn);
        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("BILL_TOTAL", currentBillTotal);
        outState.putIntArray("ANSWERS_ARRAY", answers);
        outState.putInt("POSITION", position);
        Log.d(TAG, "current bill is " + currentBillTotal);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    private void fillForm() {
        RadioGroup variants = findViewById(R.id.variants);
        findViewById(R.id.previous).setEnabled(position != 0);
        final TextView text = findViewById(R.id.question);
        Question question = this.questions.get(this.position);
        text.setText(question.getText());
        for (int index = 0; index != variants.getChildCount(); index++) {
            RadioButton button = (RadioButton) variants.getChildAt(index);
            Option option = question.getOptions().get(index);
            button.setId(option.getId());
            button.setText(option.getText());
            if (option.getId() == answers[position]) {
                button.setChecked(true);
            }
        }
    }

    private void showAnswer() {
        RadioGroup variants = findViewById(R.id.variants);
        int id = variants.getCheckedRadioButtonId();
        Question question = this.questions.get(this.position);
        Toast.makeText(
                this, "Your answer is " + id + ", correct is " + question.getAnswer(),
                Toast.LENGTH_SHORT
        ).show();
    }

    private void showWarning() {
        Toast.makeText(
                this, "Select one of the options",
                Toast.LENGTH_SHORT
        ).show();
    }

    private void nextBtn(View view) {
        RadioGroup variants = findViewById(R.id.variants);
        if (variants.getCheckedRadioButtonId()==questions.get(position).getAnswer()){
            correctAnswers++;
            wrongAnswers--;
        }
        if (position == questions.size()-1){
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("correctAnswers", correctAnswers);
            intent.putExtra("wrongAnswers", wrongAnswers);
            startActivity(intent);
        }else {
            if (variants.getCheckedRadioButtonId() != -1) {
                answers[position] = variants.getCheckedRadioButtonId();
                showAnswer();
                position++;
                fillForm();
            } else {
                showWarning();
            }
        }
    }

    private  void hintButton(View view){
        Intent intent = new Intent(this, HintActivity.class);
        TextView text = findViewById(R.id.question);
        intent.putExtra(HINT_FOR, position);
        intent.putExtra(QUESTION,text.getText().toString());
        startActivity(intent);
    }

    public void previousBtn(View view) {
        RadioGroup variants = findViewById(R.id.variants);
        if (variants.getCheckedRadioButtonId()==questions.get(position).getAnswer()){
            correctAnswers--;
            wrongAnswers++;
        }
        if (variants.getCheckedRadioButtonId() != -1) {
            answers[position] = variants.getCheckedRadioButtonId();
            showAnswer();
            position--;
            if (position==0){
                correctAnswers=0;
                wrongAnswers=3;
            }
            fillForm();
        } else {
            showWarning();
        }
    }

    public List<Question> questionsGenerator() {
        List<Question> questions = new ArrayList<>();
        String questionText[] = {"How many primitive variables does Java have?", "What is Java Virtual Machine?", "What is happen if we try unboxing null?"};
        for (int i = 1; i < 4; i++) {
            Question question = new Question(i, questionText[i - 1], optionsGenerator(i), 4);
            questions.add(question);
        }
        return questions;
    }

    public List<Option> optionsGenerator(int id) {
        List<Option> options = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            Option option = new Option(i, id + "." + i);
            options.add(option);
        }
        return options;
    }
}