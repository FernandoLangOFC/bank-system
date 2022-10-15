package bank.system.application.entity2.create;

public record CreateUserCommand( String username, String password ) {
    public static CreateUserCommand from(final String username, final String password) {
        return new CreateUserCommand(username, password);
    }
}