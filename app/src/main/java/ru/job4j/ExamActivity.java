package ru.job4j;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExamActivity extends AppCompatActivity {

    private static final String TAG = "ExamActivity";
    private int currentBillTotal;
    private int position = 0;

    private final List<Question> questions = Arrays.asList(
            new Question(
                    1, "How many primitive variables does Java have?",
                    Arrays.asList(
                            new Option(1, "1.1"), new Option(2, "1.2"),
                            new Option(3, "1.3"), new Option(4, "1.4")
                    ), 4
            ),
            new Question(
                    2, "What is Java Virtual Machine?",
                    Arrays.asList(
                            new Option(1, "2.1"), new Option(2, "2.2"),
                            new Option(3, "2.3"), new Option(4, "2.4")
                    ), 4
            ),
            new Question(
                    3, "What is happen if we try unboxing null?",
                    Arrays.asList(
                            new Option(1, "3.1"), new Option(2, "3.2"),
                            new Option(3, "3.3"), new Option(4, "3.4")
                    ), 4
            )
    );
    private int answers[] = new int[questions.size()];

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
        next.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RadioGroup variants = findViewById(R.id.variants);
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
        );

        Button previous = findViewById(R.id.previous);
        previous.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RadioGroup variants = findViewById(R.id.variants);
                        if (variants.getCheckedRadioButtonId() != -1) {
                            answers[position] = variants.getCheckedRadioButtonId();
                            showAnswer();
                            position--;
                            fillForm();
                        } else {
                            showWarning();
                        }
                    }
                }
        );
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
        findViewById(R.id.next).setEnabled(position != questions.size() - 1);
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
}