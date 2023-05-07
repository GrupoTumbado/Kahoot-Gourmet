package pw.espana.kahootgourmet.commons.game;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class Question implements Serializable, Cloneable {
    private String question;
    private int scoreValue;
    private Answer[] answers;

    public Question(String question, int scoreValue, Answer[] answers) {
        this.question = question;
        this.scoreValue = scoreValue;
        this.answers = answers;
    }

    @Override
    public Question clone() {
        try {
            return (Question) super.clone();
        } catch (CloneNotSupportedException e) {
            // should never happen since we implement Cloneable
            throw new InternalError();
        }
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(int scoreValue) {
        this.scoreValue = scoreValue;
    }

    public Answer[] getAnswers() {
        return answers;
    }

    public void setAnswers(Answer[] answers) {
        this.answers = answers;
    }

    public StringProperty questionProperty() {
        return new SimpleStringProperty(getQuestion());
    }
}
