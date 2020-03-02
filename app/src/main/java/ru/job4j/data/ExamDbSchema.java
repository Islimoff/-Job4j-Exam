package ru.job4j.data;

public class ExamDbSchema {

    public static final class ExamTable {

        public static final String NAME = "exams";

        public static final class Cols {
            public static final String ID = "id";
            public static final String NAME = "name";
            public static final String TITLE = "title";
            public static final String RESULT = "result";
            public static final String DATE = "date";
        }
    }

    public static final class QuestionTable {

        public static final String NAME = "questions";

        public static final class Cols {

            public static final String ID = "id";
            public static final String TITLE = "title";
            public static final String EXAM_ID = "exam_id";
            public static final String ANSWER_ID = "answer_id";
            public static final String POSITION = "position";
        }
    }

    public static final class OptionTable {

        public static final String NAME = "options";

        public static final class Cols {

            public static final String ID = "id";
            public static final String TEXT = "text";
            public static final String QUESTION_ID = "question_id";
            public static final String CORRECT = "correct";
        }
    }
}
