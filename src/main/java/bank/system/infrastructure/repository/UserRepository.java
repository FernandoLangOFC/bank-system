package bank.system.infrastructure.repository;


import bank.system.domain.Identifier;
import bank.system.domain.common.Status;
import bank.system.domain.user.User;

public interface UserRepository {
    Status<?> createUser(final User user);
    Status<?> updateUser(final User user);
    Status<?> retrieveUser(final Identifier<?> identifier);
    Status<?> deleteUser(final Identifier<?> identifier);
}