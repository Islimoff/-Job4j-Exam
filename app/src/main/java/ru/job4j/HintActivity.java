package ru.job4j;

import androidx.fragment.app.Fragment;

public class HintActivity extends BaseActivity {

    @Override
    public Fragment loadFragment() {
        return new HintFragment();
    }
}
