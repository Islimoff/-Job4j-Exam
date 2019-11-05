package ru.job4j;

import androidx.fragment.app.Fragment;

public class ResultActivity extends BaseActivity {

    @Override
    public Fragment loadFragment() {
        return new ResultFragment();
    }
}
