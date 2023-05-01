package pw.espana.kahootgourmet.server.messages.client.responses;

import pw.espana.kahootgourmet.server.messages.Message;
import pw.espana.kahootgourmet.server.messages.MessageId;

public class MessageResponse extends Message {
    private final int id = MessageId.CLIENT_DEBUG_RESPONSE.getValue();
}
