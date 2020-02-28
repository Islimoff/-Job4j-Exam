package ru.job4j.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.job4j.R;

public class StartExamFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.start_exam,container,false);
        Bundle args = getArguments();
        TextView title=view.findViewById(R.id.title_exam);
        title.setText(args.getString("title"));
        Button startExam = view.findViewById(R.id.start_button);

        return view;
    }
}
