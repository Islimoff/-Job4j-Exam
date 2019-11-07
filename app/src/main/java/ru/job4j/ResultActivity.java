package ru.job4j;

import androidx.fragment.app.Fragment;

public class ResultActivity extends BaseActivity {

    @Override
    public Fragment loadFragment() {
        return ResultFragment.of(getIntent().getIntExtra(ExamActivity.CORRECTANSWERS, 0)
                , getIntent().getIntExtra(ExamActivity.WRONGANSWERS, 0));
    }
}
