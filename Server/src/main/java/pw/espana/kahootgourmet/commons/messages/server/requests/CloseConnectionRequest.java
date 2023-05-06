package pw.espana.kahootgourmet.commons.messages.server.requests;

import pw.espana.kahootgourmet.commons.messages.Message;
import pw.espana.kahootgourmet.commons.messages.MessageId;

public class CloseConnectionRequest extends Message {
    public CloseConnectionRequest() {
        this.id = MessageId.SERVER_CLOSE_CONNECTION_REQUEST.getValue();
    }
}
