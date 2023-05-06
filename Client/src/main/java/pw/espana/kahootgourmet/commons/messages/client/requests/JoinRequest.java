package pw.espana.kahootgourmet.commons.messages.client.requests;

import pw.espana.kahootgourmet.commons.messages.Message;
import pw.espana.kahootgourmet.commons.messages.MessageId;

public class JoinRequest extends Message {
    private final int pin;
    private final String username;

    public JoinRequest(int pin, String username) {
        this.id = MessageId.CLIENT_JOIN_REQUEST.getValue();
        this.pin = pin;
        this.username = username;
    }

    public int getPin() {
        return pin;
    }

    public String getUsername() {
        return username;
    }
}
