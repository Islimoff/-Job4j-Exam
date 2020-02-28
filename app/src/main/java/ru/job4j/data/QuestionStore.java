package ru.job4j.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ru.job4j.data.ExamDbSchema.QuestionTable;
import ru.job4j.models.Question;

public class QuestionStore implements SqlStore<Question> {

    private static QuestionStore STORE;
    private SQLiteDatabase db;

    private QuestionStore(Context context) {
        this.db = new ExamBaseHelper(context.getApplicationContext())
                .getWritableDatabase();
    }

    public static QuestionStore getStore(Context context) {
        if (STORE == null) {
            STORE = new QuestionStore(context);
        }
        return STORE;
    }

    @Override
    public void add(Question question) {
        db.insert(QuestionTable.NAME, null, getContentValues(question));
    }

    @Override
    public void update(Question question) {
        db.update(QuestionTable.NAME, getContentValues(question),
                "id = ?", new String[]{String.valueOf(question.getId())});
    }

    @Override
    public void delete(int id) {
        db.delete(QuestionTable.NAME, "id = ?", new String[]{String.valueOf(id)});
    }

    @Override
    public List<Question> getAll() {
        List<Question> questions = new ArrayList<>();
        Cursor cursor = this.db.query(
                ExamDbSchema.ExamTable.NAME,
                null, null, null,
                null, null, null
        );
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            questions.add(new Question(cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("text")),
                    cursor.getInt(cursor.getColumnIndex("answer"))));
            cursor.moveToNext();
        }
        cursor.close();
        return questions;
    }

    @Override
    public ContentValues getContentValues(Question question) {
        return null;
    }
}
