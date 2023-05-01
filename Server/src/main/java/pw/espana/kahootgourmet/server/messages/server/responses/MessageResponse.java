package pw.espana.kahootgourmet.server.messages.server.responses;

import pw.espana.kahootgourmet.server.messages.Message;
import pw.espana.kahootgourmet.server.messages.MessageId;

public class MessageResponse extends Message {
    private final int id = MessageId.SERVER_DEBUG_RESPONSE.getValue();
}
