package bank.system.infrastructure.persistence.user;

import bank.system.domain.common.Status;
import bank.system.domain.user.User;
import bank.system.domain.user.UserGateway;
import bank.system.domain.user.UserIdentifier;
import bank.system.infrastructure.exception.OperationException;
import bank.system.infrastructure.repository.UserRepository;
import bank.system.utils.Hash;

import java.util.Optional;

public class UserPostgresGateway implements UserGateway {

    private final UserRepository userRepository;

    public UserPostgresGateway(final UserRepository anUserRepository) {
        this.userRepository = anUserRepository;
    }

    @Override
    public User create(final User user) throws OperationException {
        user.setPassword(Hash.hashPassword(user.getPassword()));
        final var createdUserStatus = userRepository.createUser(user);

        if (createdUserStatus.getStatus().equals(Status.Type.ERROR.name()))
            throw new OperationException(createdUserStatus.toString());

        return createdUserStatus.parseAndGetBody(User.class);
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