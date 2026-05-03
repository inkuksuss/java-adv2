package network.example;

import java.net.Socket;
import java.util.Objects;

public class Member {
    private String name;
    private Socket socket;

    public Member(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Member member = (Member) object;
        return Objects.equals(name, member.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
