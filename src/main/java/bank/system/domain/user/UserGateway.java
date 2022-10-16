package bank.system.domain.user;

import bank.system.infrastructure.common.Status;
import bank.system.infrastructure.exception.OperationException;

import java.util.Optional;
import java.util.UUID;

public interface UserGateway<T> {
    Status<User> create(User user) throws OperationException;
    Optional<User> findById(T id);
    void delete(T id);
    User update(User user);
    Status<User> authenticate(UserAuthRequest<UUID> userAuthRequest);
}