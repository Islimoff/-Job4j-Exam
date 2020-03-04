package ru.job4j.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Exam {

    @PrimaryKey
    private int id;
    private String name;
    private String title;
    private long date;
    private int result;

    public Exam(int id, String name, String title, long date, int result) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.date = date;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public long getDate() {
        return date;
    }

    public int getResult() {
        return result;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exam exam = (Exam) o;
        return id == exam.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", result=" + result +
                '}';
    }
}
