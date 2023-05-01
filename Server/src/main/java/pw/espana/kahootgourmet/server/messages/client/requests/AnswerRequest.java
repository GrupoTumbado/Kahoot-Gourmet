package pw.espana.kahootgourmet.server.messages.client.requests;

import pw.espana.kahootgourmet.server.messages.Message;
import pw.espana.kahootgourmet.server.messages.MessageId;

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
