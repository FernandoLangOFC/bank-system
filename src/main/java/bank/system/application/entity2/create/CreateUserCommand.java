package bank.system.application.entity2.create;

public record CreateUserCommand( String username, String email, String documentNumber, String fullName, String password, String phone ) {

    public static CreateUserCommand from(final String username, final String email, final String documentNumber, final String fullName, final String password, final String phone) {
        return new CreateUserCommand(username, email, documentNumber, fullName, password, phone);
    }
    
}