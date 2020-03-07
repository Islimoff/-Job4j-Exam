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
    public long add(Question question) {
       return db.insert(QuestionTable.NAME, null, getContentValues(question));
    }

    @Override
    public long update(Question question) {
        return db.update(QuestionTable.NAME, getContentValues(question),
                "id = ?", new String[]{String.valueOf(question.getId())});
    }

    @Override
    public long delete(int id) {
        return db.delete(QuestionTable.NAME, "id = ?", new String[]{String.valueOf(id)});
    }

    public List<Question> getByExamId(long examId){
        List<Question> questions = new ArrayList<>();
        Cursor cursor = null;
       try {
            cursor = db.query(
                   ExamDbSchema.QuestionTable.NAME,
                   null, "exam_id = "+examId, null,
                   null, null, null
           );
           cursor.moveToFirst();
           while (!cursor.isAfterLast()) {
               questions.add(getQuestion(cursor));
               cursor.moveToNext();
           }
       }finally {
           cursor.close();
       }
        return questions;
    }

    @Override
    public List<Question> getAll() {
        List<Question> questions = new ArrayList<>();
        Cursor cursor = null;
        try {
             cursor = this.db.query(
                    ExamDbSchema.QuestionTable.NAME,
                    null, null, null,
                    null, null, null
            );
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                questions.add(getQuestion(cursor));
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return questions;
    }

    @Override
    public ContentValues getContentValues(Question question) {
        ContentValues values = new ContentValues();
        values.put(QuestionTable.Cols.TITLE, question.getText());
        values.put(QuestionTable.Cols.EXAM_ID,question.getExamId());
        values.put(QuestionTable.Cols.ANSWER_ID, question.getAnswer());
        values.put(QuestionTable.Cols.POSITION, question.getPosition());
        return values;
    }

    private Question getQuestion(Cursor cursor){
        return new Question(cursor.getInt(cursor.getColumnIndex(QuestionTable.Cols.ID)),
                cursor.getLong(cursor.getColumnIndex(QuestionTable.Cols.EXAM_ID)),
                cursor.getInt(cursor.getColumnIndex(QuestionTable.Cols.POSITION)),
                cursor.getString(cursor.getColumnIndex(QuestionTable.Cols.TITLE)),
                cursor.getInt(cursor.getColumnIndex(QuestionTable.Cols.ANSWER_ID)));
    }
}
