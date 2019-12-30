package ru.job4j;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ExamsActivity extends AppCompatActivity {

    private RecyclerView recycler;

    @Override
    protected void onCreate(@Nullable Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.exams);
        recycler = findViewById(R.id.exams);
        recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
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
                Toast.makeText(ExamsActivity.this,"ADD",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.delete_item:
                Toast.makeText(ExamsActivity.this,"DELETE",Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateUI() {
        List<Exam> exams = new ArrayList<>();
        for (int index = 0; index != 100; index++) {
            exams.add(new Exam(index, String.format("Exam %s", index), System.currentTimeMillis(), 0));
        }
        recycler.setAdapter(new ExamAdapter(exams,this));
    }
}