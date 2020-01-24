package ru.job4j;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ru.job4j.data.ExamBaseHelper;
import ru.job4j.data.ExamDbSchema;
import ru.job4j.models.Exam;

public class SqlStore {

    private static SqlStore STORE;
    private SQLiteDatabase db;

    private SqlStore(Context context) {
        this.db = new ExamBaseHelper(context.getApplicationContext())
                .getWritableDatabase();
    }

    public static SqlStore getStore(Context context) {
        if (STORE == null) {
            STORE = new SqlStore(context);
        }
        return STORE;
    }

    public void addExam(Exam exam) {
        db.insert(ExamDbSchema.ExamTable.NAME, null, getContentValues(exam));
    }

    public void updateExam(Exam exam) {
        db.update(ExamDbSchema.ExamTable.NAME, getContentValues(exam),
                "id = ?", new String[]{String.valueOf(exam.getId())});
    }

    public List<Exam> getExams() {
        List<Exam> exams = new ArrayList<>();
        Cursor cursor = this.db.query(
                ExamDbSchema.ExamTable.NAME,
                null, null, null,
                null, null, null
        );
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            exams.add(new Exam(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("title")),
                    System.currentTimeMillis(),
                    100
            ));
            cursor.moveToNext();
        }
        cursor.close();
        return exams;
    }

    public void deleteExam(int id) {
        db.delete(ExamDbSchema.ExamTable.NAME, "id = ?", new String[]{String.valueOf(id)});
    }
    public void deleteAll() {
        db.delete(ExamDbSchema.ExamTable.NAME, null, null);
    }

    private ContentValues getContentValues(Exam exam) {
        ContentValues values = new ContentValues();
        values.put(ExamDbSchema.ExamTable.Cols.TITLE, exam.getName());
        return values;
    }
}

