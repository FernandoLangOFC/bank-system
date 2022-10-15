package bank.system.domain.user;

import bank.system.domain.Identifier;

import java.util.UUID;

public class UserIdentifier extends Identifier<UUID> {

    private UserIdentifier(UUID aValue) {
        super(aValue);
    }

    public static UserIdentifier from(UUID identifier) {
        return new UserIdentifier(identifier);
    }

    public static UserIdentifier from(String identifier) {
        return new UserIdentifier(UUID.fromString(identifier));
    }

    public static UserIdentifier random() {
        return new UserIdentifier(UUID.randomUUID());
    }
}