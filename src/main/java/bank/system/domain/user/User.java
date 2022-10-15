package bank.system.domain.user;

import bank.system.domain.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User extends Entity<UserIdentifier> {

    private String username;
    private String password;

    private String documentNumber;
    private String fullName;
    private String email;
    private String phone;

    private User(final UserIdentifier anId,
                 final String username,
                 final String password) {
        super(anId);
        this.username = username;
        this.password = password;

    }

    public static User create(final UserIdentifier id,
                            final String username,
                            final String password) {
        return new User(id, username, password);
    }

    public static User create(final String username, final String password) {
        return new User(UserIdentifier.random(), username, password);
    }
}