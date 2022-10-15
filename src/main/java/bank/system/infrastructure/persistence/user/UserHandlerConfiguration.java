package bank.system.infrastructure.persistence.user;

import bank.system.application.entity2.create.CreateUserHandler;
import bank.system.application.entity2.create.DefaultCreateUserHandler;
import bank.system.domain.user.UserGateway;

public class UserHandlerConfiguration {

    private final UserGateway userGateway;

    public UserHandlerConfiguration(final UserGateway userGateway) {
        this.userGateway = userGateway;
    } 

    public CreateUserHandler createUserHandler() {
        return new DefaultCreateUserHandler(userGateway);
    }
}