package ru.job4j.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ru.job4j.models.Option;

@Dao
public interface OptionDao {

    @Query("SELECT * FROM option")
    List<Option> getAll();

    @Query("SELECT * FROM option WHERE id = :id")
    Option getById(int id);

    @Insert
    void insert(Option option);

    @Update
    void update(Option option);

    @Delete
    void delete(Option option);
}
