package pw.espana.kahootgourmet.server.game;

import java.io.Serializable;

public record Question(String question, int scoreValue, Answer[] answers) implements Serializable {}