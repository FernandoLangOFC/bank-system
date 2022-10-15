package bank.system.infrastructure.persistence.user;

import bank.system.domain.user.User;
import bank.system.domain.user.UserGateway;
import bank.system.domain.user.UserIdentifier;
import bank.system.infrastructure.repository.UserRepository;

import java.util.Optional;

public class UserPostgresGateway implements UserGateway {

    private final UserRepository userRepository;

    public UserPostgresGateway(final UserRepository anUserRepository) {
        this.userRepository = anUserRepository;
    }

    @Override
    public User create(final User user) {
        final var createdUser = userRepository.createUser(user);
        return null;
    }

    @Override
    public Optional<User> findById(final UserIdentifier id) {
        return Optional.empty();
    }


    @Override
    public void delete(final UserIdentifier id) {

    }

    @Override
    public User update(final User user) {
        return null;
    }
}