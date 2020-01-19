package ru.job4j;

import androidx.fragment.app.Fragment;

public class ExamsActivity extends BaseActivity {

    @Override
    public Fragment loadFragment() {
        return new ExamListFragment();
    }
}