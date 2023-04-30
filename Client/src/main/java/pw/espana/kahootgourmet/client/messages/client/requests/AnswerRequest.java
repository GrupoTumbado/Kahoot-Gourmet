package pw.espana.kahootgourmet.client.messages.client.requests;

import pw.espana.kahootgourmet.client.messages.Message;

public class AnswerRequest extends Message {
    private final int id = 0x42;
    private final int answer;

    public AnswerRequest(int answer, String username) {
        this.answer = answer;
    }

    public int getAnswer() {
        return answer;
    }
}
