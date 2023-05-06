package pw.espana.kahootgourmet.commons.messages.server.requests;

import pw.espana.kahootgourmet.commons.messages.Message;
import pw.espana.kahootgourmet.commons.messages.MessageId;

public class FinalScoreScreenRequest extends Message {
    private final int place;
    private final int totalPoints;

    public FinalScoreScreenRequest(int place, int totalPoints) {
        this.id = MessageId.SERVER_FINAL_SCORE_SCREEN_REQUEST.getValue();
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
