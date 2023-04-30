package pw.espana.kahootgourmet.server.messages.client.requests;

import pw.espana.kahootgourmet.server.messages.Message;

public class JoinRequest extends Message {
    private final int id = 0x41;
    private final int pin;
    private final String username;

    public JoinRequest(int pin, String username) {
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
