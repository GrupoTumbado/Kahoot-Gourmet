package pw.espana.kahootgourmet.server.messages.server.requests;

import pw.espana.kahootgourmet.server.messages.Message;
import pw.espana.kahootgourmet.server.messages.MessageId;

public class ChoiceScreenRequest extends Message {
    private final int id = MessageId.SERVER_CHOICE_SCREEN_REQUEST.getValue();
}
