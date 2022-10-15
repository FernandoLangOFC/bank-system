package bank.system.domain.user;

import bank.system.domain.base.RegisteredEntityBase;
import lombok.*;

import java.util.UUID;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class User extends RegisteredEntityBase<UUID> {

    private String username;
    private String password;
    private String documentNumber;
    private String fullName;
    private String email;
    private String phone;

    // WITHOUT ID BECAUSE HIM IS AUTO-GENERATED
    private User(UserBasicAuth userBasicAuth) {
        this.username = userBasicAuth.username();
        this.password = userBasicAuth.password();
    }

    public static User create(final String username) {
        return User.create(username, null);
    }
    
    // WITHOUT ID BECAUSE HIM IS AUTO-GENERATED
    public static User create(final String username, final String password) {
        return new User(UserBasicAuth.from(username, password));
    }



}