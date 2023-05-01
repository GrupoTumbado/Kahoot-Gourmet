package pw.espana.kahootgourmet.client.messages.server.requests;

import pw.espana.kahootgourmet.client.messages.Message;
import pw.espana.kahootgourmet.client.messages.MessageId;

public class AnswerResultsScreenRequest extends Message {
    private final int id = MessageId.SERVER_ANSWER_RESULTS_SCREEN_REQUEST.getValue();
    private final boolean correct;
    private final int pointsGained;
    private final int totalPoints;
    private final int place;

    public AnswerResultsScreenRequest(boolean correct, int pointsGained, int totalPoints, int place) {
        this.correct = correct;
        this.pointsGained = pointsGained;
        this.totalPoints = totalPoints;
        this.place = place;
    }

    public boolean isCorrect() {
        return correct;
    }

    public int getPointsGained() {
        return pointsGained;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public int getPlace() {
        return place;
    }
}
