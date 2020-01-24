package ru.job4j.activities;

import androidx.fragment.app.Fragment;

import ru.job4j.fragments.GreetFragment;

public class GreetActivity extends BaseActivity {
    @Override
    public Fragment loadFragment() {
        return new GreetFragment();
    }
}
