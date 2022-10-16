package bank.system.infrastructure.persistence.user;

import bank.system.domain.user.User;
import bank.system.domain.user.UserAuthRequest;
import bank.system.domain.user.UserGateway;
import bank.system.infrastructure.common.Status;
import bank.system.infrastructure.exception.OperationException;
import bank.system.infrastructure.repository.UserRepository;
import bank.system.utils.Hash;

import static bank.system.infrastructure.common.Status.Type.ERROR;
import static bank.system.infrastructure.common.Status.Type.SUCCESS;

import java.util.Optional;
import java.util.UUID;

public final class UserPostgresGateway implements UserGateway<UUID> {

    private final UserRepository userRepository;

    public UserPostgresGateway(final UserRepository anUserRepository) {
        this.userRepository = anUserRepository;
    }

    @Override
    public Status<User> create(final User user) throws OperationException {
        user.setPassword(Hash.hashPassword(user.getPassword()));
        final var createdUserStatus = userRepository.create(user);

        if (createdUserStatus.getSituation().equals(ERROR.name()))
            throw new OperationException(createdUserStatus.toString());
        
        return createdUserStatus;
    }

    @Override
    public Optional<User> findById(final UUID id) {
        Status<User> userStatus = userRepository.findByID(id);
        if (userStatus.getSituation().equals(SUCCESS.name())) {
            return Optional.of(userStatus.getBody());
        }
        return Optional.empty();
    }


    @Override
    public void delete(final UUID id) {
        Status<?> deleteStatus = userRepository.delete(id);
        if (deleteStatus.getSituation().equals(ERROR.name()))
            throw new OperationException(deleteStatus.toString());
    }

    @Override
    public User update(final User user) {
        return null;
    }

    @Override
    public Status<User> authenticate(UserAuthRequest<UUID> userAuthRequest) {
        Status<UserAuthRequest<UUID>> userAuthStatus = userRepository.findUserAuthPassword(userAuthRequest);
        if (userAuthStatus.getSituation().equals(SUCCESS.name())) {
            boolean validPassword = Hash.validatePassword(userAuthRequest.getHashedPassword(), userAuthRequest.getUnHashedPassword());

            if (!validPassword) {
                return new Status<>(ERROR.name(), "Authentication failed, wrong password");
            }

            Status<User> userStatus = userRepository.findByID(userAuthRequest.getId());
            if (userStatus.getSituation().equals(ERROR.name())) {
                throw new OperationException(userStatus.toString());
            }

            return userStatus;
        }

        return new Status<>(ERROR.name(), userAuthStatus.getMessage());
    }
}