package network.tcp.v6;

import java.util.ArrayList;
import java.util.List;

public class SessionManagerV6 {

    private List<SessionV6> sessions = new ArrayList<>();

    public void add(SessionV6 session) {
        sessions.add(session);
    }

    public void remove(SessionV6 session) {
        sessions.remove(session);
    }

    public synchronized void closeAll() {
        for (SessionV6 session : sessions) {
            session.close();
        }
        sessions.clear();
    }
}
