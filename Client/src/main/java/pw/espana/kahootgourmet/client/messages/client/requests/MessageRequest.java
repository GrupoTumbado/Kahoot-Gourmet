package pw.espana.kahootgourmet.client.messages.client.requests;

import pw.espana.kahootgourmet.client.messages.Message;
import pw.espana.kahootgourmet.client.messages.MessageId;

public class MessageRequest extends Message {
    private final int id = MessageId.CLIENT_DEBUG_REQUEST.getValue();
}
