package bank.system.infrastructure.persistence.user;

import bank.system.domain.user.User;
import bank.system.domain.user.UserGateway;
import bank.system.infrastructure.exception.OperationException;
import bank.system.infrastructure.repository.UserRepository;
import bank.system.utils.Hash;

import static bank.system.infrastructure.common.Status.Type.ERROR;

import java.util.Optional;
import java.util.UUID;

public final class UserPostgresGateway implements UserGateway<UUID> {

    private final UserRepository userRepository;

    public UserPostgresGateway(final UserRepository anUserRepository) {
        this.userRepository = anUserRepository;
    }

    @Override
    public User create(final User user) throws OperationException {
        user.setPassword(Hash.hashPassword(user.getPassword()));
        final var createdUserStatus = userRepository.create(user);

        if (createdUserStatus.getStatus().equals(ERROR.name()))
            throw new OperationException(createdUserStatus.toString());
        
        return (User) createdUserStatus.getBody();
    }

    @Override
    public Optional<User> findById(final UUID id) {
        return Optional.empty();
    }


    @Override
    public void delete(final UUID id) {

    }

    @Override
    public User update(final User user) {
        return null;
    }
}