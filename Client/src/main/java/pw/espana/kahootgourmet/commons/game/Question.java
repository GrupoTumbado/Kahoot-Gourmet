package pw.espana.kahootgourmet.commons.game;

public record Question(String question, int scoreValue, Answer[] answers, int waitTime) {}