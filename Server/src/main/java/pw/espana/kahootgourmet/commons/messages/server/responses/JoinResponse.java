package pw.espana.kahootgourmet.commons.messages.server.responses;

import pw.espana.kahootgourmet.commons.messages.Message;
import pw.espana.kahootgourmet.commons.messages.MessageId;

public class JoinResponse extends Message {
    private final boolean joined;
    private String errorMessage;
    private int totalQuestions;
    private int answerTime;

    public JoinResponse(String errorMessage) {
        this.id = MessageId.SERVER_JOIN_RESPONSE.getValue();
        this.joined = false;
        this.errorMessage = errorMessage;
    }

    public JoinResponse(int totalQuestions, int answerTime) {
        this.id = MessageId.SERVER_JOIN_RESPONSE.getValue();
        this.joined = true;
        this.totalQuestions = totalQuestions;
        this.answerTime = answerTime;
    }

    public boolean isJoined() {
        return joined;
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
