package pw.espana.kahootgourmet.client.messages.server.requests;

import pw.espana.kahootgourmet.client.messages.Message;
import pw.espana.kahootgourmet.client.messages.MessageId;

public class ChoiceScreenRequest extends Message {
    private final int id = MessageId.SERVER_CHOICE_SCREEN_REQUEST.getValue();
}
