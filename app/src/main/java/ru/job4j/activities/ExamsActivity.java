package ru.job4j.activities;

import androidx.fragment.app.Fragment;

import ru.job4j.fragments.ExamListFragment;

public class ExamsActivity extends BaseActivity {

    @Override
    public Fragment loadFragment() {
        return new ExamListFragment();
    }
}