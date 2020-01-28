package ru.job4j.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ru.job4j.data.ExamDbSchema.*;

public class ExamBaseHelper extends SQLiteOpenHelper {

    private static final String DB = "exams.db";
    private static final int VERSION = 1;

    public ExamBaseHelper(Context context) {
        super(context, DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EXAMS_TABLE = "create table " + ExamTable.NAME + " (" +
                "id integer primary key autoincrement, " +
                ExamTable.Cols.NAME + ", " +
                ExamTable.Cols.TITLE + ", " +
                ExamTable.Cols.DATE + ", " +
                ExamTable.Cols.RESULT + " " +
                ")";
        String CREATE_QUESTIONS_TABLE = "create table " + QuestionTable.NAME + " (" +
                "id integer primary key autoincrement, " +
                QuestionTable.Cols.NAME + ", " +
                QuestionTable.Cols.TITLE + ", " +
                QuestionTable.Cols.EXAM_ID + ", " +
                QuestionTable.Cols.ANSWER_ID + ", " +
                QuestionTable.Cols.POSITION + " " +
                ")";

        String CREATE_OPTIONS_TABLE = "create table " + OptionTable.NAME + " (" +
                OptionTable.Cols.TEXT + ", " +
                OptionTable.Cols.QUESTION_ID + ", " +
                OptionTable.Cols.CORRECT + " " +
                ")";

        db.execSQL(CREATE_EXAMS_TABLE);
        db.execSQL(CREATE_QUESTIONS_TABLE);
        db.execSQL(CREATE_OPTIONS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + ExamTable.NAME);
            db.execSQL("DROP TABLE IF EXISTS " + QuestionTable.NAME);
            db.execSQL("DROP TABLE IF EXISTS " + OptionTable.NAME);
            onCreate(db);
        }
    }
}
