package pw.espana.kahootgourmet.server.messages.server.requests;

import pw.espana.kahootgourmet.server.messages.Message;
import pw.espana.kahootgourmet.server.messages.MessageId;

public class FinalScoreScreenRequest extends Message {
    private final int id = MessageId.SERVER_FINAL_SCORE_SCREEN_REQUEST.getValue();
    private final int place;
    private final int totalPoints;

    public FinalScoreScreenRequest(int place, int totalPoints) {
        this.place = place;
        this.totalPoints = totalPoints;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public int getPlace() {
        return place;
    }
}