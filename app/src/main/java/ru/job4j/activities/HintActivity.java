package ru.job4j.activities;

import androidx.fragment.app.Fragment;

import ru.job4j.fragments.HintFragment;

public class HintActivity extends BaseActivity {

    @Override
    public Fragment loadFragment() {
        return HintFragment.of(getIntent().getIntExtra(ExamActivity.HINT_FOR, 0)
                , getIntent().getStringExtra(ExamActivity.QUESTION));
    }
}
