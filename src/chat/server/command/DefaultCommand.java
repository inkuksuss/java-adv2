package chat.server.command;

import chat.server.Session;
import chat.server.SessionManager;

import java.io.IOException;
import java.util.Arrays;

public class DefaultCommand implements Command {

    private final SessionManager sessionManager;

    public DefaultCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String[] args, Session session) throws IOException {
        session.send("처리 할 수 없는 명령어입니다. " + Arrays.toString(args));
    }
}
