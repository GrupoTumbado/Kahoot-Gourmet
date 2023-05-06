package pw.espana.kahootgourmet.commons.messages.client.requests;

import pw.espana.kahootgourmet.commons.messages.Message;
import pw.espana.kahootgourmet.commons.messages.MessageId;

public class AnswerRequest extends Message {
    private final int answer;

    public AnswerRequest(int answer) {
        this.id = MessageId.CLIENT_ANSWER_REQUEST.getValue();
        this.answer = answer;
    }

    public int getAnswer() {
        return answer;
    }
}
