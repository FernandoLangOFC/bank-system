package bank.system.domain.user;

import bank.system.domain.Entity;
import bank.system.domain.Identifier;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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

    // WITHOUT ID BECAUSE HIM IS AUTO-GENERATED
    private User( final String username, final String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public void setId(UserIdentifier userIdentifier) {
        this.id = userIdentifier;
    }

    public static User create(final UserIdentifier id,
                            final String username,
                            final String password) {
        return new User(id, username, password);
    }

    // WITHOUT ID BECAUSE HIM IS AUTO-GENERATED
    public static User create(final String username, final String password) {
        return new User(username, password);
    }
}