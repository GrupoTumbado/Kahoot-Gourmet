package pw.espana.kahootgourmet.commons.messages.client.responses;

import pw.espana.kahootgourmet.commons.messages.Message;
import pw.espana.kahootgourmet.commons.messages.MessageId;

public class MessageResponse extends Message {
    public MessageResponse() {
        this.id = MessageId.CLIENT_DEBUG_RESPONSE.getValue();
    }
}
