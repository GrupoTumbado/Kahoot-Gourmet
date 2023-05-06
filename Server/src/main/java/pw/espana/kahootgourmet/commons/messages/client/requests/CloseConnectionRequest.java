package pw.espana.kahootgourmet.commons.messages.client.requests;

import pw.espana.kahootgourmet.commons.messages.Message;
import pw.espana.kahootgourmet.commons.messages.MessageId;

public class CloseConnectionRequest extends Message {
    public CloseConnectionRequest() {
        this.id = MessageId.CLIENT_CLOSE_CONNECTION_REQUEST.getValue();
    }
}
