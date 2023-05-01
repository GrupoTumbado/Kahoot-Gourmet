package pw.espana.kahootgourmet.client.messages;

import java.io.Serializable;
import java.time.Instant;

public class Message implements Serializable {
    private final int id = MessageId.SERVER_DEBUG_REQUEST.getValue();
    private final Instant createdAt = Instant.now();

    public Message() {}

    public int getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
