package network.example;

import java.net.Socket;
import java.util.Objects;

public class Session {

    private String name;

    private Socket socket;

    public Session(String name, Socket socket) {
        this.name = name;
        this.socket = socket;
    }

    public String getName() {
        return name;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Session session = (Session) object;
        return Objects.equals(name, session.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
