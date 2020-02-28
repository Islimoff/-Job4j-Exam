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
    public void add(Option option) {
        db.insert(OptionTable.NAME, null, getContentValues(option));
    }

    @Override
    public void update(Option option) {
        db.update(OptionTable.NAME, getContentValues(option),
                "id = ?", new String[]{String.valueOf(option.getId())});
    }

    @Override
    public void delete(int id) {
        db.delete(OptionTable.NAME, "id = ?", new String[]{String.valueOf(id)});
    }

    @Override
    public List<Option> getAll() {
        List<Option> options = new ArrayList<>();
        Cursor cursor = this.db.query(
                ExamDbSchema.ExamTable.NAME,
                null, null, null,
                null, null, null
        );
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            options.add(new Option(cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("text"))));
            cursor.moveToNext();
        }
        cursor.close();
        return options;
    }

    @Override
    public ContentValues getContentValues(Option option) {
        ContentValues values = new ContentValues();
        return values;
    }
}
