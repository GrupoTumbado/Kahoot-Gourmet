package pw.espana.kahootgourmet.server.game;

public record Question(String question, int scoreValue, Answer[] answers, int waitTime) {}