package pw.espana.kahootgourmet.client.messages.server.requests;

import pw.espana.kahootgourmet.client.messages.Message;

public class LoadingScreenRequest extends Message {
    private final int id = 0x01;
    private int waitTime;

    public LoadingScreenRequest() {}

    public LoadingScreenRequest(int waitTime) {
        this.waitTime = waitTime;
    }

    public int getWaitTime() {
        return waitTime;
    }
}
