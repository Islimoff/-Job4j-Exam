package ru.job4j.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ru.job4j.data.ExamDbSchema.ExamTable;
import ru.job4j.models.Option;
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
    public long add(Exam exam) {
        return db.insert(ExamTable.NAME, null, getContentValues(exam));
    }

    @Override
    public long update(Exam exam) {
        return db.update(ExamTable.NAME, getContentValues(exam),
                "id = ?", new String[]{String.valueOf(exam.getId())});
    }

    @Override
    public long delete(int id) {
        return db.delete(ExamTable.NAME, "id = ?", new String[]{String.valueOf(id)});
    }

    public Exam getById(int id) {
        Cursor cursor = db.query(
                ExamTable.NAME,
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
                ExamTable.NAME,
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
        values.put(ExamTable.Cols.NAME, exam.getName());
        values.put(ExamTable.Cols.TITLE, exam.getTitle());
        values.put(ExamTable.Cols.RESULT, exam.getResult());
        values.put(ExamTable.Cols.DATE, exam.getDate());
        return values;
    }

    public void deleteAll() {
        db.delete(ExamTable.NAME, null, null);
    }

    private void generateData() {
        Cursor cursor = this.db.query(
                ExamTable.NAME,
                null, null, null,
                null, null, null
        );
        if (cursor.moveToFirst()) {
            return;
        } else {
            for (int i = 1; i < 5; i++) {
                Exam exam = new Exam(0, "exam" + i,
                        "some text describing the exam" + i, 0, 0);
               long examId=this.add(exam);
                for (int j = 1; j < 4; j++) {
                    Question question= new Question(0,examId,j,
                            "Some Question"+j+" for Exam"+examId,"Variant"+j
                    );
                    long questionId=QuestionStore.getStore(context).add(question);
                    for (int r = 1; r < 5; r++) {
                        int correct=0;
                        if(r==1){
                            correct=1;
                        }
                        Option option= new Option(0,questionId,
                                "Some variant"+r+" for question"+j,correct
                        );
                        OptionStore.getStore(context).add(option);
                    }
                }
            }
        }
    }

    private Exam getExam(Cursor cursor) {
        return new Exam(
                cursor.getInt(cursor.getColumnIndex(ExamTable.Cols.ID)),
                cursor.getString(cursor.getColumnIndex(ExamTable.Cols.NAME)),
                cursor.getString(cursor.getColumnIndex(ExamTable.Cols.TITLE)),
                cursor.getLong(cursor.getColumnIndex(ExamTable.Cols.DATE)),
                cursor.getInt(cursor.getColumnIndex(ExamTable.Cols.RESULT))
        );
    }
}

