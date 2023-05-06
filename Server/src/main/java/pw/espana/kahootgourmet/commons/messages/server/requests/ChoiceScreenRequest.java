package pw.espana.kahootgourmet.commons.messages.server.requests;

import pw.espana.kahootgourmet.commons.messages.Message;
import pw.espana.kahootgourmet.commons.messages.MessageId;

public class ChoiceScreenRequest extends Message {
    public ChoiceScreenRequest() {
        this.id = MessageId.SERVER_CHOICE_SCREEN_REQUEST.getValue();
    }
}
