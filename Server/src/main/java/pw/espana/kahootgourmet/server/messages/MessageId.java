package pw.espana.kahootgourmet.server.messages;

import java.util.HashMap;
import java.util.Map;

public enum MessageId {
    SERVER_DEBUG_REQUEST(0x00),
    SERVER_LOADING_SCREEN_REQUEST(0x01),
    SERVER_CHOICE_SCREEN_REQUEST(0x02),
    SERVER_ANSWER_RESULTS_SCREEN_REQUEST(0x03),
    SERVER_FINAL_SCORE_SCREEN_REQUEST(0x04),
    SERVER_DEBUG_RESPONSE(0x80),
    SERVER_JOIN_RESPONSE(0x81),
    CLIENT_DEBUG_REQUEST(0x40),
    CLIENT_JOIN_REQUEST(0x41),
    CLIENT_ANSWER_REQUEST(0x42),
    CLIENT_CLOSE_CONNECTION_REQUEST(0x7F),
    CLIENT_DEBUG_RESPONSE(0xC0),
    UNKNOWN_MESSAGE(0xFF);

    private int value;
    private static Map map = new HashMap<>();
    private MessageId(int value) {
        this.value = value;
    }

    static {
        for (MessageId messageId : MessageId.values()) {
            map.put(messageId.value, messageId);
        }
    }

    public static MessageId valueOf(int messageId) {
        return (MessageId) map.get(messageId);
    }

    public int getValue() {
        return value;
    }
}
