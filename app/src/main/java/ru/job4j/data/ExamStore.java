package ru.job4j.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ru.job4j.models.Exam;

public class ExamStore implements SqlStore<Exam> {

    private static ExamStore STORE;
    private SQLiteDatabase db;

    private ExamStore(Context context) {
        this.db = new ExamBaseHelper(context.getApplicationContext())
                .getWritableDatabase();
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

    @Override
    public List<Exam> getAll() {
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
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getLong(cursor.getColumnIndex("date")),
                    cursor.getInt(cursor.getColumnIndex("result"))
            ));
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

    private void generateData(){
        Cursor cursor = this.db.query(
                ExamDbSchema.ExamTable.NAME,
                null, null, null,
                null, null, null
        );
        if(cursor.moveToFirst()){
            return;
        }else{
            for (int i=0;i<5;i++){
                Exam exam=new Exam(0,"exam"+i,0,0);
                this.add(exam);
            }
        }
    }
}

