package ru.job4j;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.result_activity);
        Bundle arguments = getIntent().getExtras();
        final TextView text=findViewById(R.id.answers);
        int correctAnswers=arguments.getInt("correctAnswers");
        int wrongAnswers=arguments.getInt("wrongAnswers");
        text.setText("Correct Answers: "+correctAnswers+"\nWrong Answers: "+wrongAnswers);

    }
}
