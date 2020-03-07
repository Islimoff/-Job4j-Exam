package ru.job4j.activities;

import androidx.fragment.app.Fragment;

import ru.job4j.fragments.ExamFragment;
import ru.job4j.fragments.HintFragment;

public class HintActivity extends BaseActivity {

    @Override
    public Fragment loadFragment() {
        return HintFragment.of(getIntent().getIntExtra(ExamFragment.HINT_FOR, 0)
                , getIntent().getStringExtra(ExamFragment.QUESTION));
    }
}
