package bank.system.application.entity2.create;

import bank.system.domain.user.User;
import bank.system.domain.user.UserGateway;
import bank.system.infrastructure.common.Status;

import java.util.UUID;

public class DefaultCreateUserHandler implements CreateUserHandler {

    private final UserGateway<UUID> userGateway;

    public DefaultCreateUserHandler(final UserGateway<UUID> userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public String execute(final CreateUserCommand createUserCommand) {
        final var aName = createUserCommand.username();
        final var aPassword = createUserCommand.password();

        final var anUser = User.create(aName, aPassword);
        Status<User> userStatus = userGateway.create(anUser);
        return userStatus.getMessage();
    }
}