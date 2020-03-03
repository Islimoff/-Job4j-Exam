package ru.job4j.models;

public class Question {

    private int id;
    private String text;
    private long examId;
    private int answerId;
    private int position;

    public Question(int id, long examId, int position, String text, int answerId) {
        this.id = id;
        this.examId=examId;
        this.position=position;
        this.text = text;
        this.answerId = answerId;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getAnswer() {
        return answerId;
    }

    public void setAnswerId(int answerId){
        this.answerId=answerId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public long getExamId() {
        return examId;
    }

    public void setExamId(long examId) {
        this.examId = examId;
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
                ", answer=" + answerId +
                '}';
    }
}
