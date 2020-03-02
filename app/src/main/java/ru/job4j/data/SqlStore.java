package ru.job4j.data;

import android.content.ContentValues;

import java.util.List;

public interface SqlStore<T> {

    long add(T object);

    long update(T object);

    long delete(int id);

    List<T> getAll();

    ContentValues getContentValues(T object);
}
