package bank.system.infrastructure.repository;


import bank.system.domain.Identifier;
import bank.system.domain.common.Status;
import bank.system.domain.user.User;

import java.util.UUID;

public interface UserRepository {
    Status<?> createUser(final User user);
    Status<?> updateUser(final User user);
    Status<?> retrieveUser(final Identifier<UUID> identifier);
    Status<?> deleteUser(final Identifier<UUID> identifier);

}