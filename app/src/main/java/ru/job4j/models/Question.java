package ru.job4j.models;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Question {

    @PrimaryKey
    private int id;
    private String text;
    private int answer;

    public Question(int id, String text, int answer) {
        this.id = id;
        this.text = text;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getAnswer() {
        return answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id == question.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", answer=" + answer +
                '}';
    }
}
