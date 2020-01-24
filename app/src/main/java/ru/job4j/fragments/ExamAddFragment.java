package ru.job4j.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import ru.job4j.R;
import ru.job4j.SqlStore;
import ru.job4j.models.Exam;

public class ExamAddFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.exam_add, container, false);
        final EditText edit = view.findViewById(R.id.name);
        Button save = view.findViewById(R.id.save);
        save.setOnClickListener(
                btn -> {
                    Exam exam = new Exam(0, edit.getText().toString(),
                            System.currentTimeMillis(),
                            100);
                    SqlStore.getStore(getContext()).addExam(exam);
                    FragmentManager fm = getFragmentManager();
                    fm.beginTransaction()
                            .replace(R.id.content, new ExamListFragment())
                            .addToBackStack(null)
                            .commit();
                }
        );
        return view;
    }
}
