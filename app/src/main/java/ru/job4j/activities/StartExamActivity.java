package ru.job4j.activities;

import androidx.fragment.app.Fragment;

import ru.job4j.fragments.StartExamFragment;

public class StartExamActivity extends BaseActivity{

    @Override
    public Fragment loadFragment() {
        return new StartExamFragment();
    }
}
