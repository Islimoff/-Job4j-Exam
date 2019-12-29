package ru.job4j;

import androidx.fragment.app.Fragment;

public class DateTimeActivity extends BaseActivity {

    @Override
    public Fragment loadFragment() {
        return new DateTimeFragment();
    }
}
