package bank.system.domain.user;

import bank.system.infrastructure.exception.OperationException;

import java.util.Optional;

public interface UserGateway<T> {
    User create(User user) throws OperationException;
    Optional<User> findById(T id);
    void delete(T id);
    User update(User user);
}