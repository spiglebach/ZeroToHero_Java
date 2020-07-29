package hu.zerotohero.verseny.websocket.entity;

import java.security.Principal;

public class User implements Principal {
    private String uuid;

    public User(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object another) {
        return another.hashCode() == hashCode();
    }

    @Override
    public String toString() {
        return uuid;
    }

    @Override
    public int hashCode() {
        return 31 * uuid.hashCode() + 17;
    }

    @Override
    public String getName() {
        return uuid;
    }
}
