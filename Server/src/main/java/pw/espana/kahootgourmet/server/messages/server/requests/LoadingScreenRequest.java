package pw.espana.kahootgourmet.server.messages.server.requests;

import pw.espana.kahootgourmet.server.messages.Message;
import pw.espana.kahootgourmet.server.messages.MessageId;

public class LoadingScreenRequest extends Message {
    private final int id = MessageId.SERVER_LOADING_SCREEN_REQUEST.getValue();
    private int waitTime;

    public LoadingScreenRequest() {}

    public LoadingScreenRequest(int waitTime) {
        this.waitTime = waitTime;
    }

    public int getWaitTime() {
        return waitTime;
    }
}
