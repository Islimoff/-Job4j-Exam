package ru.job4j.models;

public class Question {

    private int id;
    private String text;
    private long examId;
    private String answer;
    private int position;

    public Question(int id, long examId, int position, String text, String answer) {
        this.id = id;
        this.examId=examId;
        this.position=position;
        this.text = text;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getAnswer() {
        return answer;
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
                ", answer=" + answer +
                '}';
    }
}
