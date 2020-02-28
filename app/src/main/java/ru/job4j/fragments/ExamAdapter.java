package ru.job4j.fragments;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.job4j.R;
import ru.job4j.activities.ExamActivity;
import ru.job4j.models.Exam;

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ExamHolder> {

    private final List<Exam> exams;
    private Context parent;

    public ExamAdapter(List<Exam> exams, Context parent) {
        this.exams = exams;
        this.parent = parent;
    }

    public class ExamHolder extends RecyclerView.ViewHolder {

        private View view;

        public ExamHolder(@NonNull View view) {
            super(view);
            this.view = itemView;
        }
    }

    @NonNull
    @Override
    public ExamHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.info_exam, parent, false);
        return new ExamHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamHolder holder, int position) {
        final Exam exam = this.exams.get(position);
        TextView text = holder.view.findViewById(R.id.info);
        TextView result=holder.view.findViewById(R.id.result);
        TextView date=holder.view.findViewById(R.id.date);
        text.setText(exam.getName());
        result.setText(String.valueOf(exam.getResult())+"%");
        date.setText(String.valueOf(exam.getDate()));
        text.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(parent, ExamActivity.class);
                        parent.startActivity(intent);
                    }
                }
        );
    }

    @Override
    public int getItemCount() {
        return this.exams.size();
    }
}
