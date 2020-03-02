package ru.job4j.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ru.job4j.data.ExamDbSchema.OptionTable;
import ru.job4j.models.Option;

public class OptionStore implements SqlStore<Option> {

    private static OptionStore STORE;
    private SQLiteDatabase db;

    private OptionStore(Context context) {
        this.db = new ExamBaseHelper(context.getApplicationContext())
                .getWritableDatabase();
    }

    public static OptionStore getStore(Context context) {
        if (STORE == null) {
            STORE = new OptionStore(context);
        }
        return STORE;
    }

    @Override
    public long add(Option option) {
        return db.insert(OptionTable.NAME, null, getContentValues(option));
    }

    @Override
    public long update(Option option) {
       return db.update(OptionTable.NAME, getContentValues(option),
                "id = ?", new String[]{String.valueOf(option.getId())});
    }

    @Override
    public long delete(int id) {
          return db.delete(OptionTable.NAME, "id = ?", new String[]{String.valueOf(id)});
    }

    public List<Option> getByQuestionId(long questionId){
        List<Option> options = new ArrayList<>();
        Cursor cursor = this.db.query(
                ExamDbSchema.OptionTable.NAME,
                null, "question_id = "+questionId, null,
                null, null, null
        );
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            options.add(new Option(cursor.getInt(cursor.getColumnIndex(OptionTable.Cols.ID)),
                    cursor.getLong(cursor.getColumnIndex(OptionTable.Cols.QUESTION_ID)),
                    cursor.getString(cursor.getColumnIndex(OptionTable.Cols.TEXT)),
                    cursor.getInt(cursor.getColumnIndex(OptionTable.Cols.CORRECT))));
            cursor.moveToNext();
        }
        cursor.close();
        return options;
    }

    @Override
    public List<Option> getAll() {
        List<Option> options = new ArrayList<>();
        Cursor cursor = this.db.query(
                ExamDbSchema.OptionTable.NAME,
                null, null, null,
                null, null, null
        );
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            options.add(new Option(cursor.getInt(cursor.getColumnIndex(OptionTable.Cols.ID)),
                    cursor.getLong(cursor.getColumnIndex(OptionTable.Cols.QUESTION_ID)),
                    cursor.getString(cursor.getColumnIndex(OptionTable.Cols.TEXT)),
                    cursor.getInt(cursor.getColumnIndex(OptionTable.Cols.CORRECT))));
            cursor.moveToNext();
        }
        cursor.close();
        return options;
    }

    @Override
    public ContentValues getContentValues(Option option) {
        ContentValues values = new ContentValues();
        values.put(OptionTable.Cols.QUESTION_ID,option.getQuestion_id());
        values.put(OptionTable.Cols.CORRECT,option.getCorrect());
        values.put(OptionTable.Cols.TEXT,option.getText());
        return values;
    }
}
