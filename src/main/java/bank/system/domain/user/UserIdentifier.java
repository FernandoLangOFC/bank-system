package bank.system.domain.user;

import bank.system.domain.base.Identifier;

import java.util.UUID;

public class UserIdentifier extends Identifier<UUID> {

    private UserIdentifier(UUID aValue) {
        super(aValue);
    }

    public static UserIdentifier random() {
        return new UserIdentifier(UUID.randomUUID());
    }
}