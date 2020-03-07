package ru.job4j.activities;

import androidx.fragment.app.Fragment;

import ru.job4j.fragments.ExamFragment;
import ru.job4j.fragments.StartExamFragment;

public class ExamActivity extends BaseActivity {
    @Override
    public Fragment loadFragment() {
        return ExamFragment.of(getIntent().getIntExtra(StartExamFragment.EXAM_ID, 0));
    }
}