package pw.espana.kahootgourmet.server.game;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Questionnaire implements Serializable {
    private int currentQuestion = 0;
    private final List<Question> questions = new ArrayList<>();
    private final int answerTime;
    private final int waitTime;

    public Questionnaire() {
        this.answerTime = 60;
        this.waitTime = 5;
    }

    public Questionnaire(int answerTime, int waitTime) {
        this.answerTime = answerTime;
        this.waitTime = waitTime;
    }

    public Question getCurrentQuestion() {
        return questions.get(currentQuestion);
    }

    public boolean moreQuestionsAvailable() {
        return currentQuestion < questions.size() - 1;
    }

    public void advanceQuestion() {
        currentQuestion++;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public int getQuestionCount() {
        return questions.size();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public int getAnswerTime() {
        return answerTime;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public static Questionnaire loadFromFile(String pathToQuestionnaire) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(pathToQuestionnaire));

            Questionnaire questionnaire = (Questionnaire) ois.readObject();
            ois.close();

            return questionnaire;
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public void saveToFile(String pathToQuestionnaire) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(pathToQuestionnaire));
            oos.writeObject(this);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
