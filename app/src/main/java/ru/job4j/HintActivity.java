package ru.job4j;

import androidx.fragment.app.Fragment;

public class HintActivity extends BaseActivity {

    @Override
    public Fragment loadFragment() {
        return HintFragment.of(getIntent().getIntExtra(ExamActivity.HINT_FOR, 0)
                , getIntent().getStringExtra(ExamActivity.QUESTION));
    }
}
