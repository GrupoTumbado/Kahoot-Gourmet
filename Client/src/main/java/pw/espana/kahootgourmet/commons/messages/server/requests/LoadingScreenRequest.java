package pw.espana.kahootgourmet.commons.messages.server.requests;

import pw.espana.kahootgourmet.commons.messages.Message;
import pw.espana.kahootgourmet.commons.messages.MessageId;

public class LoadingScreenRequest extends Message {
    private int waitTime;

    public LoadingScreenRequest() {
        this.id = MessageId.SERVER_LOADING_SCREEN_REQUEST.getValue();
    }

    public LoadingScreenRequest(int waitTime) {
        this.id = MessageId.SERVER_LOADING_SCREEN_REQUEST.getValue();
        this.waitTime = waitTime;
    }

    public int getWaitTime() {
        return waitTime;
    }
}
