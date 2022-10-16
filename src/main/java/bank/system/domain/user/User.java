package bank.system.domain.user;

import bank.system.domain.base.RegisteredEntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class User extends RegisteredEntityBase<UUID> {

    // Entity<Idendifier<UUID>> no make sence in this context
    // Is justifiable to use in Multi Class field as Id, in a web app for example
    // An EntityBase<UUID> is a much simpler version of this pattern.
    // ID is not something whose type can be changed so easily, so there's no need to
    // decouple it from the entity.
    // However, i made this BaseEntity to be in the middle, a simpler version of what
    // it was before.
    // Don't feed Cats with Bird's seeds, it's not sustainable

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