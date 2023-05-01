package pw.espana.kahootgourmet.client.messages.server.responses;

import pw.espana.kahootgourmet.client.messages.Message;
import pw.espana.kahootgourmet.client.messages.MessageId;

public class MessageResponse extends Message {
    private final int id = MessageId.SERVER_DEBUG_RESPONSE.getValue();
}
