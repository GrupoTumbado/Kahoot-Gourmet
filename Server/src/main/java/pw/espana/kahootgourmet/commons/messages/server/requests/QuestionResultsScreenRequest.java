package pw.espana.kahootgourmet.commons.messages.server.requests;

import pw.espana.kahootgourmet.commons.messages.Message;
import pw.espana.kahootgourmet.commons.messages.MessageId;

public class QuestionResultsScreenRequest extends Message {
    private final boolean correct;
    private final int pointsGained;
    private final int totalPoints;
    private final int place;

    public QuestionResultsScreenRequest(boolean correct, int pointsGained, int totalPoints, int place) {
        this.id = MessageId.SERVER_ANSWER_RESULTS_SCREEN_REQUEST.getValue();
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
