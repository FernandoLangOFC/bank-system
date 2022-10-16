package bank.system.infrastructure.persistence.user;

import bank.system.application.user.create.CreateUserHandler;
import bank.system.application.user.create.DefaultCreateUserHandler;
import bank.system.domain.user.UserGateway;

import java.util.UUID;

public class UserHandlerConfiguration {

    private final UserGateway<UUID> userGateway;

    public UserHandlerConfiguration(final UserGateway<UUID> userGateway) {
        this.userGateway = userGateway;
    } 

    public CreateUserHandler createUserHandler() {
        return new DefaultCreateUserHandler(userGateway);
    }
}