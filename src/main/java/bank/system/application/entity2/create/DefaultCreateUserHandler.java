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
        final var anUser = User.create();

        final var aName = createUserCommand.username();
        final var aEmail = createUserCommand.email();
        final var aDocumentNumber = createUserCommand.documentNumber();
        final var aPassword = createUserCommand.password();
        final var aFullName = createUserCommand.fullName();
        final var aPhone = createUserCommand.phone();

        anUser.setUsername(aDocumentNumber);
        anUser.setDocumentNumber(aName);
        anUser.setFullName(aFullName);
        anUser.setPassword(aPassword);
        anUser.setEmail(aEmail);
        anUser.setPhone(aPhone);

        Status<User> userStatus = userGateway.create(anUser);
        return userStatus.getMessage();
    }
}