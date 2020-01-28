package ru.job4j.data;

import java.util.ArrayList;
import java.util.List;

import ru.job4j.models.Option;
import ru.job4j.models.Question;

public class Store {

    private static final Store STORE = new Store();
    private static final List<Question> questions = questionsGenerator();
    private static int answers[] = new int[questions.size()];

    private Store() {
    }

    public static Store getStore() {
        return STORE;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public Question getQuestion(int position) {
        return questions.get(position);
    }

    public int getAnswer(int position) {
        return answers[position];
    }

    public void setAnswer(int position, int checkedRadioButtonId) {
        answers[position] = checkedRadioButtonId;
    }

    private static List<Question> questionsGenerator() {
        List<Question> questions = new ArrayList<>();
        String questionText[] = {"How many primitive variables does Java have?", "What is Java Virtual Machine?", "What is happen if we try unboxing null?"};
        for (int i = 1; i < 4; i++) {
            Question question = new Question(i, questionText[i - 1], optionsGenerator(i), 4);
            questions.add(question);
        }
        return questions;
    }

    private static List<Option> optionsGenerator(int id) {
        List<Option> options = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            Option option = new Option(i, id + "." + i);
            options.add(option);
        }
        return options;
    }
}
