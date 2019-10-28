package ru.job4j;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class HintActivity extends AppCompatActivity {

    private final Map<Integer, String> answers = new HashMap<Integer, String>();

    public HintActivity() {
        this.answers.put(0, "Hint 1");
        this.answers.put(1, "Hint 2");
        this.answers.put(2, "Hint 3");
    }

    @Override
    protected void onCreate(@Nullable Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.hint_activity);
        final TextView question = findViewById(R.id.question);
        final TextView answer = findViewById(R.id.hint);
        int page = getIntent().getIntExtra(ExamActivity.HINT_FOR, 0);
        String questionText=getIntent().getStringExtra(ExamActivity.QUESTION);
        question.setText(questionText);
        answer.setText(this.answers.get(page));
        Button back = findViewById(R.id.back);
        back.setOnClickListener(this::backBtn);
    }

    private void backBtn(View view){
        onBackPressed();
    }
}
