package ru.job4j.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import ru.job4j.R;
import ru.job4j.activities.ExamActivity;

public class StartExamFragment extends Fragment {

    private Bundle args;
    public static final String EXAM_ID = "id";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.start_exam, container, false);
        args = getArguments();
        TextView title = view.findViewById(R.id.title_exam);
        title.setText(args.getString("title"));
        Button startExam = view.findViewById(R.id.start_button);
        startExam.setOnClickListener(this::startFragment);
        return view;
    }

    private void startFragment(View view) {
        Intent intent = new Intent(getContext(), ExamActivity.class);
        intent.putExtra("id", args.getInt("examId"));
        startActivity(intent);
    }
}
