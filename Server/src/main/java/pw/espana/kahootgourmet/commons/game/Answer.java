package pw.espana.kahootgourmet.commons.game;

import java.io.Serializable;

public record Answer(String answer, boolean correct) implements Serializable {}