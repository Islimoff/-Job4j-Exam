package ru.job4j.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ru.job4j.dao.ExamDao;
import ru.job4j.dao.OptionDao;
import ru.job4j.dao.QuestionDao;
import ru.job4j.models.Exam;
import ru.job4j.models.Option;
import ru.job4j.models.Question;

@Database(entities = {Exam.class, Option.class, Question.class},version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ExamDao examDao();
    public abstract OptionDao optionDao();
    public abstract QuestionDao questionDao();
}
