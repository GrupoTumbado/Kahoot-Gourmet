package pw.espana.kahootgourmet.client.game;

public record Question(String question, int scoreValue, Answer[] answers, int waitTime) {}