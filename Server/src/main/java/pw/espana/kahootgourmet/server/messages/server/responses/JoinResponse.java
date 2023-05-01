package pw.espana.kahootgourmet.server.messages.server.responses;

import pw.espana.kahootgourmet.server.messages.Message;
import pw.espana.kahootgourmet.server.messages.MessageId;

public class JoinResponse extends Message {
    private final int id = MessageId.SERVER_JOIN_RESPONSE.getValue();
    private final boolean joined;
    private String errorMessage;
    private int totalQuestions;
    private int answerTime;

    public JoinResponse(String errorMessage) {
        this.joined = false;
        this.errorMessage = errorMessage;
    }

    public JoinResponse(int totalQuestions, int answerTime) {
        this.joined = true;
        this.totalQuestions = totalQuestions;
        this.answerTime = answerTime;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public int getAnswerTime() {
        return answerTime;
    }
}
