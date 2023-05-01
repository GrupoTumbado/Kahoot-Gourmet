package pw.espana.kahootgourmet.client.messages.client.requests;

import pw.espana.kahootgourmet.client.messages.Message;
import pw.espana.kahootgourmet.client.messages.MessageId;

public class AnswerRequest extends Message {
    private final int id = MessageId.CLIENT_ANSWER_REQUEST.getValue();
    private final int answer;

    public AnswerRequest(int answer, String username) {
        this.answer = answer;
    }

    public int getAnswer() {
        return answer;
    }
}
