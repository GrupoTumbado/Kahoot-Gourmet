package pw.espana.kahootgourmet.commons.messages.client.requests;

import pw.espana.kahootgourmet.commons.messages.Message;
import pw.espana.kahootgourmet.commons.messages.MessageId;

public class MessageRequest extends Message {
    public MessageRequest() {
        this.id = MessageId.CLIENT_DEBUG_REQUEST.getValue();
    }
}
