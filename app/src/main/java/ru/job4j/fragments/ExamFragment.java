package ru.job4j.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.job4j.R;

public class ExamFragment extends Fragment {

   private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.activity_exam,container,false);
        Bundle args=getArguments();
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

    private void fillForm(){
        RadioGroup variants = view.findViewById(R.id.variants);
        TextView questionText = view.findViewById(R.id.question);
        Question question = QuestionStore.getStore().getQuestion(position);
    }
}
