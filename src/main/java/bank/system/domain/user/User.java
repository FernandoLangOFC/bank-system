package bank.system.domain.user;

import bank.system.domain.base.RegisteredEntityBase;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class User extends RegisteredEntityBase<UUID> {

    private String username;
    private String password;
    private String documentNumber;
    private String fullName;
    private String email;
    private String phone;

    private User(UUID uuid) {
        super(uuid);
    }

    // WITHOUT ID BECAUSE HIM IS AUTO-GENERATED
    public static User create() {
        return new User();
    }
    
    public static User createWithRandomUUID() {
        return new User(UUID.randomUUID());
    }
}