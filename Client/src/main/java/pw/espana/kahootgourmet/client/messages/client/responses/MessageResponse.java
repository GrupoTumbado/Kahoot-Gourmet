package pw.espana.kahootgourmet.client.messages.client.responses;

import pw.espana.kahootgourmet.client.messages.Message;
import pw.espana.kahootgourmet.client.messages.MessageId;

public class MessageResponse extends Message {
    private final int id = MessageId.CLIENT_DEBUG_RESPONSE.getValue();
}
