package ru.job4j.data;

import android.content.ContentValues;

import java.util.List;

public interface SqlStore<T> {

    void add(T object);

    void update(T object);

    void delete(int id);

    List<T> getAll();

    ContentValues getContentValues(T object);
}
