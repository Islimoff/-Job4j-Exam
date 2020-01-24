package ru.job4j.activities;

import androidx.fragment.app.Fragment;

import ru.job4j.fragments.ResultFragment;

public class ResultActivity extends BaseActivity {

    @Override
    public Fragment loadFragment() {
        return ResultFragment.of(getIntent().getIntExtra(ExamActivity.CORRECTANSWERS, 0)
                , getIntent().getIntExtra(ExamActivity.WRONGANSWERS, 0));
    }
}
