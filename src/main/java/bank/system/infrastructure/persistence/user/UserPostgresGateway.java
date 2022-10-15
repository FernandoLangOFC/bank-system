package bank.system.infrastructure.persistence.user;

import bank.system.domain.user.User;
import bank.system.domain.user.UserAuth;
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
    public User create(final User user) throws OperationException {
        user.setPassword(Hash.hashPassword(user.getPassword()));
        final var createdUserStatus = userRepository.create(user);

        if (createdUserStatus.getStatus().equals(ERROR.name()))
            throw new OperationException(createdUserStatus.toString());
        
        return createdUserStatus.getBody();
    }

    @Override
    public Optional<User> findById(final UUID id) {
        Status<User> userStatus = userRepository.findByID(id);
        if (userStatus.getStatus().equals(SUCCESS.name())) {
            return Optional.of(userStatus.getBody());
        }
        return Optional.empty();
    }


    @Override
    public void delete(final UUID id) {
        Status<?> deleteStatus = userRepository.delete(id);
        if (deleteStatus.getStatus().equals(ERROR.name()))
            throw new OperationException(deleteStatus.toString());
    }

    @Override
    public User update(final User user) {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Status<?> authenticate(String authType, String search, String unHashPassword) {
        Status<?> userAuthStatus = userRepository.findUserAuthPassword(authType, search);
        if (userAuthStatus.getStatus().equals(SUCCESS.name())) {
            UserAuth<UUID> userAuth = userAuthStatus.parseAndGetBody(UserAuth.class);
            boolean validPassword = Hash.validatePassword(userAuth.password(), unHashPassword);
            if (!validPassword) {
                userAuthStatus.setStatus(ERROR.name());
                userAuthStatus.setMessage("Authentication failed, wrong password");
                return userAuthStatus;
            }

            Status<User> userStatus = userRepository.findByID(userAuth.id());
            if (userStatus.getStatus().equals(ERROR.name())) {
                throw new OperationException(userStatus.toString());
            }

            return userStatus;
        }
        return userAuthStatus;
    }
}