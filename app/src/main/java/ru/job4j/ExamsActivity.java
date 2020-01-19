package ru.job4j;

<<<<<<< HEAD
import androidx.fragment.app.Fragment;

public class ExamsActivity extends BaseActivity {

    @Override
    public Fragment loadFragment() {
        return new ExamListFragment();
=======
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ExamsActivity extends AppCompatActivity implements ConfirmDeleteExamsListFragment.ConfirmDeleteExamsListener {

    private RecyclerView recycler;
    private List<Exam> exams;

    @Override
    protected void onCreate(@Nullable Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.exams);
        recycler = findViewById(R.id.exams);
        recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        exams = new ArrayList<>();
        for (int index = 0; index != 100; index++) {
            exams.add(new Exam(index, String.format("Exam %s", index), System.currentTimeMillis(), 0));
        }
        updateUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.exams,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                exams.add(new Exam(exams.size(), String.format("Exam %s", exams.size()), System.currentTimeMillis(), 0));
                updateUI();
                return true;
            case R.id.delete_item:
                DialogFragment dialog = new ConfirmDeleteExamsListFragment();
                dialog.show(getSupportFragmentManager(), "dialog_tag");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateUI() {
        recycler.setAdapter(new ExamAdapter(exams,this));
>>>>>>> 0d5845f3bd06a0ef7edcaeed3adfb2f7f3125dc8
    }

    @Override
    public void onPositiveDialogClick(DialogFragment dialog) {
        exams.clear();
        updateUI();
    }

    @Override
    public void onNegativeDialogClick(DialogFragment dialog) {
        return;
    }
}