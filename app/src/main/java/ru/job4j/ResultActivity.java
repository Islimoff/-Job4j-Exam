package ru.job4j;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle state) {
        super.onCreate(state);
        TextView textView = new TextView(this);
        Bundle arguments = getIntent().getExtras();

        if (arguments != null) {
            String correctAnswers = arguments.get("name").toString();
            String wrongAnswers = arguments.getString("company");
            textView.setText("correctAnswers: " + correctAnswers + "\nwrongAnswers: " + wrongAnswers);
        }
        setContentView(textView);
    }
}
