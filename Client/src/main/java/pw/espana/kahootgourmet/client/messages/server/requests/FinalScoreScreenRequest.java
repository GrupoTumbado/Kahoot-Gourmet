package pw.espana.kahootgourmet.client.messages.server.requests;

import pw.espana.kahootgourmet.server.messages.Message;

public class FinalScoreScreenRequest extends Message {
    private final int id = 0x04;
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
