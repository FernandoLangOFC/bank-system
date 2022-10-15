package bank.system.domain.user;

import java.util.Optional;

public interface UserGateway {
    User create(User user);
    Optional<User> findById(UserIdentifier id);
    void delete(UserIdentifier id);
    User update(User user);
}