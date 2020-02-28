package ru.job4j.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.security.cert.PKIXRevocationChecker;
import java.util.ArrayList;
import java.util.List;

import ru.job4j.models.Exam;
import ru.job4j.models.Question;

public class ExamStore implements SqlStore<Exam> {

    private static ExamStore STORE;
    private SQLiteDatabase db;
    private Context context;

    private ExamStore(Context context) {
        this.db = new ExamBaseHelper(context.getApplicationContext())
                .getWritableDatabase();
        this.context=context;
        generateData();
    }

    public static ExamStore getStore(Context context) {
        if (STORE == null) {
            STORE = new ExamStore(context);
        }
        return STORE;
    }

    @Override
    public void add(Exam exam) {
        db.insert(ExamDbSchema.ExamTable.NAME, null, getContentValues(exam));
    }

    @Override
    public void update(Exam exam) {
        db.update(ExamDbSchema.ExamTable.NAME, getContentValues(exam),
                "id = ?", new String[]{String.valueOf(exam.getId())});
    }

    @Override
    public void delete(int id) {
        db.delete(ExamDbSchema.ExamTable.NAME, "id = ?", new String[]{String.valueOf(id)});
    }

    public Exam findById(int id) {
        Cursor cursor = db.query(
                ExamDbSchema.ExamTable.NAME,
                null, "id = ?", null,
                null, null, null
        );
        cursor.moveToFirst();
        Exam exam = getExam(cursor);
        cursor.close();
        return exam;
    }

    @Override
    public List<Exam> getAll() {
        List<Exam> exams = new ArrayList<>();
        Cursor cursor = db.query(
                ExamDbSchema.ExamTable.NAME,
                null, null, null,
                null, null, null
        );
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            exams.add(getExam(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return exams;
    }

    @Override
    public ContentValues getContentValues(Exam exam) {
        ContentValues values = new ContentValues();
        values.put(ExamDbSchema.ExamTable.Cols.NAME, exam.getName());
        values.put(ExamDbSchema.ExamTable.Cols.TITLE, exam.getTitle());
        values.put(ExamDbSchema.ExamTable.Cols.RESULT, exam.getResult());
        values.put(ExamDbSchema.ExamTable.Cols.DATE, exam.getDate());
        return values;
    }

    public void deleteAll() {
        db.delete(ExamDbSchema.ExamTable.NAME, null, null);
    }

    private void generateData() {
        Cursor cursor = this.db.query(
                ExamDbSchema.ExamTable.NAME,
                null, null, null,
                null, null, null
        );
        if (cursor.moveToFirst()) {
            return;
        } else {
            for (int i = 0; i < 5; i++) {
                Exam exam = new Exam(0, "exam" + i,
                        "some text describing the exam" + i, 0, 0);
               int examId=this.add(exam);
                for (int j = 1; j < 4; j++) {
                    Question question= new Question(0,examId,j,
                            "Some Question"+j,"Variant"+j
                    );
                    int questionId=QuestionStore.getStore(context).add(question);
                    for (int r = 1; r < 4; r++) {
                        Option option= new Option(0,questionId,r,
                                "Some Question"+r,"Variant"+r
                        );
                    }
                }
            }
        }
    }

    private Exam getExam(Cursor cursor) {
        return new Exam(
                cursor.getInt(cursor.getColumnIndex("id")),
                cursor.getString(cursor.getColumnIndex("name")),
                cursor.getString(cursor.getColumnIndex("title")),
                cursor.getLong(cursor.getColumnIndex("date")),
                cursor.getInt(cursor.getColumnIndex("result"))
        );
    }
}

