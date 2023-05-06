package pw.espana.kahootgourmet.commons.game;

import java.io.Serializable;

public record Question(String question, int scoreValue, Answer[] answers) implements Serializable {}