package pw.espana.kahootgourmet.server.messages.client.requests;

import pw.espana.kahootgourmet.server.messages.Message;
import pw.espana.kahootgourmet.server.messages.MessageId;

public class MessageRequest extends Message {
    private final int id = MessageId.CLIENT_DEBUG_REQUEST.getValue();
}
