package pw.espana.kahootgourmet.server.game;

import java.io.Serializable;

public record Answer(String answer, boolean correct) implements Serializable {}