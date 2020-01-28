package ru.job4j.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.job4j.R;
import ru.job4j.data.SqlStore;
import ru.job4j.models.Exam;

public class ExamListFragment extends Fragment {

    private RecyclerView recycler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.exams, container, false);
        this.recycler = view.findViewById(R.id.exams);
        this.recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    private void updateUI() {
        List<Exam> exams = SqlStore.getStore(getActivity()).getExams();
        this.recycler.setAdapter(new ExamAdapter(exams));
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.exams, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_exam:
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.content , new ExamAddFragment())
                        .addToBackStack(null)
                        .commit();
                return true;
            case R.id.delete_exams:
                SqlStore.getStore(getContext()).deleteAll();
                updateUI();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class ExamHolder extends RecyclerView.ViewHolder {

        private View view;

        public ExamHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
        }
    }

    public class ExamAdapter extends RecyclerView.Adapter<ExamHolder> {

        private final List<Exam> exams;

        public ExamAdapter(List<Exam> exams) {
            this.exams = exams;
        }

        @NonNull
        @Override
        public ExamHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.info_exam, parent, false);
            return new ExamHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ExamHolder holder, int i) {
            Exam exam = this.exams.get(i);
            TextView text = holder.view.findViewById(R.id.info);
            if ((i % 2) == 0) {
                holder.view.setBackgroundColor(Color.parseColor("#d8d8d8"));
            }
            text.setText(exam.getName());
            holder.view.findViewById(R.id.edit)
                    .setOnClickListener(
                            btn -> {
                                FragmentManager fm = getFragmentManager();
                                Fragment fragment = new ExamUpdateFragment();
                                Bundle bundle = new Bundle();
                                bundle.putInt("id", exam.getId());
                                bundle.putString("name", exam.getName());
                                fragment.setArguments(bundle);
                                fm.beginTransaction()
                                        .replace(R.id.content, fragment)
                                        .addToBackStack(null)
                                        .commit();
                            }
                    );

            holder.view.findViewById(R.id.delete)
                    .setOnClickListener(
                            btn -> { SqlStore.getStore(getActivity()).deleteExam(exam.getId());
                                exams.remove(exam);
                                notifyItemRemoved(i);
                            }
                    );
        }

        @Override
        public int getItemCount() {
            return this.exams.size();
        }
    }
}
