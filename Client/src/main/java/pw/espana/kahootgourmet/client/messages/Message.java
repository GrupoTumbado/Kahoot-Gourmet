package pw.espana.kahootgourmet.client.messages;

import java.io.Serializable;
import java.time.Instant;

public class Message implements Serializable {
    private final int id = 0x00;
    private final Instant createdAt = Instant.now();

    public Message() {}

    public int getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
