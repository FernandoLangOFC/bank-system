package bank.system.application.entity2.create;

import bank.system.domain.user.User;
import bank.system.domain.user.UserGateway;

public class DefaultCreateUserHandler extends CreateUserHandler {

    private static final String SUCCESSFULLY_CREATED = "User created successfully!";

    private final UserGateway userGateway;

    public DefaultCreateUserHandler(final UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public String execute(final CreateUserCommand createUserCommand) {
        final var aName = createUserCommand.username();
        final var aPassword = createUserCommand.password();

        final var anUser = User.create(aName, aPassword);
        userGateway.create(anUser);

        return SUCCESSFULLY_CREATED;
    }
}