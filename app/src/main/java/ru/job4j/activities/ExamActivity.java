package ru.job4j.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import ru.job4j.R;
import ru.job4j.data.Store;
import ru.job4j.fragments.ConfirmHintDialogFragment;
import ru.job4j.models.Question;

public class ExamActivity extends AppCompatActivity implements ConfirmHintDialogFragment.ConfirmHintDialogListener {

    private static final String TAG = "ExamActivity";
    private int currentBillTotal;
    private int position = 0;
    public static final String HINT_FOR = "hint_for";
    public static final String QUESTION = "question";
    public static final String CORRECTANSWERS = "correctAnswers";
    public static final String WRONGANSWERS = "wrongAnswers";
    int correctAnswers;
    int wrongAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            currentBillTotal = 0;
        } else {
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
        Button back = findViewById(R.id.back_to_list);
        back.setOnClickListener(this::backBtn);
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("BILL_TOTAL", currentBillTotal);
        outState.putInt("POSITION", position);
        Log.d(TAG, "current bill is " + currentBillTotal);
    }

    private void fillForm() {
        RadioGroup variants = findViewById(R.id.variants);
        findViewById(R.id.previous).setEnabled(position != 0);
        final TextView text = findViewById(R.id.question);
        Question question = Store.getStore().getQuestion(position);
        text.setText(question.getText());
//        for (int index = 0; index != variants.getChildCount(); index++) {
//            RadioButton button = (RadioButton) variants.getChildAt(index);
////          Option option = question.getOptions().get(index);
//            button.setId(option.getId());
//            button.setText(option.getText());
//            if (option.getId() == Store.getStore().getAnswer(position)) {
//                button.setChecked(true);
//            }
//        }
    }

    private void showAnswer() {
        RadioGroup variants = findViewById(R.id.variants);
        int id = variants.getCheckedRadioButtonId();
        Question question = Store.getStore().getQuestion(position);
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
        Store.getStore().setAnswer(position, variants.getCheckedRadioButtonId());
        if (position == Store.getStore().getQuestions().size() - 1) {
            calculateResults();
            Intent intent = new Intent(this, ResultActivity.class);
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

    private void hintButton(View view) {
        DialogFragment dialog = new ConfirmHintDialogFragment();
        dialog.show(getSupportFragmentManager(), "dialog_tag");
    }

    public void previousBtn(View view) {
        RadioGroup variants = findViewById(R.id.variants);
        if (variants.getCheckedRadioButtonId() != -1) {
            Store.getStore().setAnswer(position, variants.getCheckedRadioButtonId());
            showAnswer();
            position--;
            fillForm();
        } else {
            showWarning();
        }
    }

    private void calculateResults() {
        correctAnswers = 0;
        wrongAnswers = Store.getStore().getQuestions().size();
//        for (int i = 0; i < Store.getStore().getQuestions().size(); i++) {
//            if (Store.getStore().getAnswer(i) == Store.getStore().getQuestion(i).getAnswer()) {
//                correctAnswers++;
//                wrongAnswers--;
//            }
//        }
    }

    private void backBtn(View view) {
        onBackPressed();
    }

    @Override
    public void onPositiveDialogClick(DialogFragment dialog) {
        Intent intent = new Intent(this, HintActivity.class);
        TextView text = findViewById(R.id.question);
        intent.putExtra(HINT_FOR, position);
        intent.putExtra(QUESTION, text.getText().toString());
        startActivity(intent);
    }

    @Override
    public void onNegativeDialogClick(DialogFragment dialog) {
        Toast.makeText(this, "Молодец!!!", Toast.LENGTH_SHORT).show();
    }
}