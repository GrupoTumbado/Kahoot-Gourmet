package pw.espana.kahootgourmet.server.messages.server.responses;

import pw.espana.kahootgourmet.server.messages.Message;

public class JoinResponse extends Message {
    private final int id = 0x81;
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
