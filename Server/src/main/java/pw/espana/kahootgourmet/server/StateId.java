package pw.espana.kahootgourmet.server;

import pw.espana.kahootgourmet.commons.messages.MessageId;

import java.util.HashMap;
import java.util.Map;

public enum StateId {
    IDLE(0x00),
    LISTENING_FOR_CONNECTIONS(0x01),
    SHOWING_QUESTION(0x02),
    SHOWING_QUESTION_AND_ANSWERS(0x03),
    COUNT_ANSWERS(0x04),
    SHOWING_ANSWER(0x05),
    HANDLE_NEXT_QUESTION(0x06),
    SHOWING_SCOREBOARD(0x07),
    UNKNOWN_STATE(0xFF);

    private int value;
    private static Map map = new HashMap<>();
    private StateId(int value) {
        this.value = value;
    }

    static {
        for (StateId stateId : StateId.values()) {
            map.put(stateId.value, stateId);
        }
    }

    public static StateId valueOf(int stateId) {
        return (StateId) map.get(stateId);
    }

    public int getValue() {
        return value;
    }
}
