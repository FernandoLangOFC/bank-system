package bank.system.domain.user;

import bank.system.infrastructure.exception.OperationException;

import java.util.Optional;

public interface UserGateway {
    User create(User user) throws OperationException;
    Optional<User> findById(UserIdentifier id);
    void delete(UserIdentifier id);
    User update(User user);
}