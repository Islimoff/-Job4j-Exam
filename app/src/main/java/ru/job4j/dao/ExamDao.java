package ru.job4j.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ru.job4j.models.Exam;

@Dao
public interface ExamDao {

    @Query("SELECT * FROM exam")
    List<Exam> getAll();

    @Query("SELECT * FROM exam WHERE id = :id")
    Exam getById(int id);

    @Insert
    void insert(Exam exam);

    @Update
    void update(Exam exam);

    @Delete
    void delete(Exam exam);
}
