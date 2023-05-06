package pw.espana.kahootgourmet.commons.messages.server.responses;

import pw.espana.kahootgourmet.commons.messages.Message;
import pw.espana.kahootgourmet.commons.messages.MessageId;

public class MessageResponse extends Message {
    public MessageResponse() {
        id = MessageId.SERVER_DEBUG_RESPONSE.getValue();
    }
}
